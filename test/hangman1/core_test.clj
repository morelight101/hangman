(ns hangman1.core-test
  (:require [clojure.test :refer :all]
            [hangman1.core :refer :all]))



(deftest validword?
  (testing "Does word to be guessed only contain allowed letters"
    (is (= ( 1) ))))

(deftest guessedletters
  (testing "Is the Input part of the allowed letters, Input: *allowedletters* Input"
    (is (= 2 (guessedletters *allowedletters* '["1" "a" "b"])))))

(deftest  #correctguesses
  (testing "Number of correct guesses calculated: input: Input selectedword"
    (is (= 3 (correctguesses '[\P \a \l] "Palm")))))

(deftest #pointscalculated
  (testing "Number of Points calculated"
    (is (= 24 (totalpoints  (correctguesses '[\P \a \l] "Palm") *pointsvalue*)))))

(deftest numblettersremaining
  (testing "Number of remaining letters"
    (is (= 1 (numblettersremaining "Palm" '[\P \a \l] )))))


(deftest gameover?
  (testing "Number of guesses exceeds number of allowed guesses"
    (is (true (gameover? *maxguesses* (count guessedletters))))))


