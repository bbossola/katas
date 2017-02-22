import unittest

from core.biz import Railway, Filters
from math import inf;

class Validation(unittest.TestCase):

    # Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
    def setUp(self):
        self.railway = Railway()

        self.alfa = self.railway.newStation('A')
        self.beta = self.railway.newStation('B')
        self.cola = self.railway.newStation('C')
        self.deta = self.railway.newStation('D')
        self.elah = self.railway.newStation('E')

        self.alfa.link(self.beta, 5)
        self.beta.link(self.cola, 4)
        self.cola.link(self.deta, 8)
        self.deta.link(self.cola, 8)
        self.deta.link(self.elah, 6)
        self.alfa.link(self.deta, 5)
        self.cola.link(self.elah, 2)
        self.elah.link(self.beta, 3)
        self.alfa.link(self.elah, 7)
        

    # 1. The distance of the route A-B-C
    def test_01(self):
        route = self.railway.best_direct_route(self.alfa,self.beta,self.cola)
        self.assertEqual(9, route.distance())

    # 2. The distance of the route A-D.
    def test_02(self):
        route = self.railway.best_direct_route(self.alfa,self.deta)
        self.assertEqual(5, route.distance())

    # 3. The distance of the route A-D-C.
    def test_03(self):
        route = self.railway.best_direct_route(self.alfa, self.deta, self.cola)
        self.assertEqual(13, route.distance())

    # 4. The distance of the route A-E-B-C-D.
    def test_04(self):
        route = self.railway.best_direct_route(self.alfa, self.elah, self.beta, self.cola, self.deta)
        self.assertEqual(22, route.distance())

    # 5. The distance of the route A-E-D.
    def test_05(self):
        route = self.railway.best_direct_route(self.alfa, self.elah, self.deta)
        self.assertEqual(inf, route.distance())

    # 6. The number of trips starting at C and ending at C with a maximum of 3 stops.
    def test_06(self):
        routes = self.railway.all_routes(Filters.max_stops(3), self.cola, self.cola)
        self.assertEqual(2, len(routes))

    # 7. The number of trips starting at A and ending at C with exactly 4 stops.
    def test_07(self):
        routes = self.railway.all_routes(Filters.exact_stops(3), self.alfa, self.cola)
        self.assertEqual(3, len(routes))

    # 8. The length of the shortest route (in terms of distance to travel) from A to C.
    def test_08(self):
        self.assertEqual(9, self.alfa.route_to(self.cola).distance())

    # 9. The length of the shortest route (in terms of distance to travel) from B to B.
    def test_09(self):
        self.assertEqual(9, self.railway.best_route(self.beta, self.beta).distance())

    # 10. The number of different routes from C to C with a distance of less than 30
    def test_10(self):
        routes = self.railway.all_routes(Filters.distance_less_than(30), self.cola, self.cola)
        self.assertEqual(7, len(routes))

