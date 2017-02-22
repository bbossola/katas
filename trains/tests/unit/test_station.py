import unittest

from core.biz import Station
from math import inf

class Validation(unittest.TestCase):
    
    def test_not_linked(self):
        alfa = Station('A')
        beta = Station('B')
        
        alfa.link(beta, 20)

        self.assertEqual(inf, beta.route_to(alfa).distance())
        
    def test_directed_link(self):
        alfa = Station('A')
        beta = Station('B')
        
        alfa.link(beta, 20)

        self.assertEqual(20, alfa.route_to(beta).distance())
        

    def test_indirected_link(self):
        alfa = Station('A')
        beta = Station('B')
        cola = Station('C')
         
        alfa.link(beta, 20)
        beta.link(cola, 5)
 
        self.assertEqual(25, alfa.route_to(cola).distance())
        
    def test_indirected_link_long(self):
        alfa = Station('A')
        beta = Station('B')
        cola = Station('C')
        deta = Station('D')
        elah = Station('E')
         
        alfa.link(beta, 20)
        beta.link(cola, 5)
        cola.link(deta, 5)
        deta.link(elah, 10)
 
        self.assertEqual(40, alfa.route_to(elah).distance())

    
