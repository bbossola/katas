from math import inf


class Context():

    def __init__(self, max_len, direct=False, deep=False):
        self._max_len = max_len
        self._direct = direct
        self._deep = deep
        self._routes = []

    def routes(self):
        return self._routes

    def max_depth(self):
        return self._max_len

    def direct(self):
        return self._direct

    def deep(self):
        return self._deep

    def store(self, route):
        self._routes.append(route)

    def __len__(self):
        return len(self._routes)


def defaut_context():
    return Context(10)


class Filters():

    @staticmethod
    def all():
        return lambda context, route: True

    @staticmethod
    def max_stops(max_stops):
        return lambda context, route: route.stops() < max_stops

    @staticmethod
    def distance_less_than(max_distance):
        return lambda context, route: route.distance() < max_distance

    @staticmethod
    def compose(*filters):
        def check(context, route):
            for filtrum in filters:
                if filtrum(context, route) == False:
                    return False
            return True

        return check

    @staticmethod
    def valid(station_from, station_to):
        def check(context, route):
            return route.start() == station_from and route.end() == station_to

        return check

    @staticmethod
    def exact_stops(max_stops):
        return lambda context, route: route.stops() == max_stops


class Route():

    def __init__(self, steps=[], distance=0):
        self._steps = steps
        self._distance = distance

    def via(self, station_from, station_to):
        new_steps = list(self._steps)
        new_steps.append(station_to)
        new_distance = self._distance + station_from.distance_to(station_to)
        return Route(new_steps, new_distance)

    def concatenate(self, other_route):
        new_steps = list(self._steps)
        new_steps.append(other_route._steps)
        new_distance = self._distance + other_route._distance
        return Route(new_steps, new_distance)

    def distance(self):
        return self._distance

    def stops(self):
        return max(0, len(self._steps) - 2)

    def shorter_than(self, other):
        return self._distance < other.distance()

    def start(self):
        return self._steps[0] if len(self._steps) > 0 else None

    def end(self):
        return self._steps[-1] if len(self._steps) > 0 else None

    def __str__(self):
        text = str(self._distance) + "-"
        for step in self._steps:
            text = text + str(step)
        return text

    def __len__(self):
        return len(self._steps)

NO_ROUTE = Route([], inf)


class Station():

    def __init__(self, iden):
        self._id = iden
        self._links = {}

    def link(self, other, distance):
        self._links[other] = distance

    # A -> B -> C
    def route_to(self, other, context=defaut_context(), filtrum=Filters.all()):
        return self._route_to(Route([self]), other, context, filtrum)

    def _route_to(self, route, other, context, filtrum):
        if self.connected_to(other):
            result = route.via(self, other)
            if filtrum(context, result):
                context.store(result)
                if context.deep() == False:
                    return result

        if len(route) < context.max_depth() and context.direct() == False:
            result = NO_ROUTE
            for station in self._links:
                route_try = station._route_to(route.via(self, station), other, context, filtrum)
                result = route_try if route_try.shorter_than(result) else result
                if filtrum(context, result):
                    context.store(route)

            return result
        else:
            return NO_ROUTE


    def connected_to(self, other):
        return other in self._links

    def distance_to(self, other):
        return self._links[other] if self.connected_to(other) else inf

    def id(self):
        return self._id

    def __str__(self):
        return self._id


class Railway():

    def __init__(self):
        self._size = 0

    def newStation(self, name):
        station = Station(name)
        self._size = self._size + 1
        return station

    def all_routes(self, filters, *stations):
        context = Context(max_len=10, deep=True)
        self._route(stations, context, filters)
        return context.routes()

    def best_route(self, *stations):
        context = Context(max_len=self._size)
        return self._route(stations, context)

    def best_direct_route(self, *stations):
        context = Context(max_len=self._size, direct=True)
        return self._route(stations, context)

    def _route(self, stations, context, user_filter=Filters.all()):
        result = Route()
        start = None
        for station in stations:
            if start is None:
                start = station
            else:
                filtrum = Filters.compose(user_filter, Filters.valid(start, station))
                result = result.concatenate(start.route_to(station, context, filtrum))
                start = station

        return result
