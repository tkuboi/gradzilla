import unittest

from lab1 import get_max
from lab1 import reverse
from lab1 import search
from lab1 import fib
from lab1 import factorial_iter
from lab1 import factorial_rec

class MyTest(unittest.TestCase):
    def runTest(self):
        with self.subTest(msg="testing get_max"):
            self.test_get_max()
        with self.subTest(msg="testing reverse"):
            self.test_reverse()
        with self.subTest(msg="testing search"):
            self.test_search()
        with self.subTest(msg="testing fib"):
            self.test_fib()
        with self.subTest(msg="testing factorial"):
            self.test_factorial()

    def test_get_max(self):
        arr = [1,2,3,4,5]
        self.assertEqual(get_max(arr), 5)
        arr = [1, 1, 1, 0]
        self.assertEqual(get_max(arr), 1)
        self.assertEqual(get_max([]), None)

    def test_reverse(self):
        self.assertEqual(reverse("qweEerty"), "ytreEewq")
        self.assertEqual(reverse("aa"), "aa")
        self.assertEqual(reverse("a"), "a")
        self.assertEqual(reverse(""), "")

    def test_search(self):
        arr = [1,2,3,4,5]
        self.assertEqual(search(arr, 5), 4)
        arr = [1,2,3,4,5]
        self.assertEqual(search(arr, 2), 1)
        arr = [1, 1, 1]
        self.assertEqual(search(arr, 5), None)
        arr = []
        self.assertEqual(search(arr, 5), None)

    def test_fib(self):
        def fib_numbers(n):
            sequence = []
            for i in range(n+1):
                sequence.append(fib(i))
            return sequence

        #this will test your fib function by calling it multiple times
        self.assertEqual(fib_numbers(10),
                         [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55])

    def test_factorial(self):
        self.assertEqual(factorial_iter(0), 1)
        self.assertEqual(factorial_iter(1), 1)
        self.assertEqual(factorial_iter(3), 6)
        self.assertEqual(factorial_rec(0), 1)
        self.assertEqual(factorial_rec(1), 1)
        self.assertEqual(factorial_rec(3), 6)

def get_score(max_score, result):
    score = max_score
    for error in result.errors:
        #print("-10 points for ", error[1])
        score -= 30

    for failure in result.failures:
        #print("-5 points for ", failure[1])
        score -= 5

    return max(0, score)

def main():
    runner = unittest.TextTestRunner()
    result = runner.run(MyTest())
    score = get_score(90, result)
    print("SCORE:{%s}\n" % (score))
    return score

if __name__ == '__main__':
    main()

