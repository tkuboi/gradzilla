import unittest
from lab1 import func1, func2

class MyTest(unittest.TestCase):
    def runTest(self):
        self.test1()
        self.test2()

    def test1(self):
        self.assertTrue(func1(1));

    def test2(self):
        self.assertFalse(func2(1));

def get_score(max_score, result):
    score = max_score
    for error in result.errors:
        #print("-10 points for ", error[1])
        score -= 10 

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

