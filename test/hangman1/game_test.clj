(ns hangman1.game-test
  (:require [clojure.test :refer [deftest is testing]]
            [hangman1.game :as sut]))

;ein verlorenes Spiel (der Spieler konnte das Wort nicht erraten)
(def game-lost
  {:word-to-guess "tree"
   :correct-guesses #{"t"}
   :tries-left 0})

;;ein gewonnenes Spiel (der Spieler konnte das Wort erraten)
(def game-won
  {:word-to-guess "tree"
   :correct-guesses #{"t" "r" "e"}
   :tries-left 2})

;;ein laufendes Spiel
(def game-ongoing
  {:word-to-guess "tree"
   :correct-guesses #{"t"}
   :tries-left 2})

;;ein neues Spiel
(def game-new
  {:word-to-guess "tree"
   :correct-guesses #{}
   :tries-left 5})

(deftest word->letters
  (testing "`word->letters`"
    (is (= ["t" "r" "e" "e"]
           (sut/word->letters "tree")))))

(deftest game-won
  (testing "`game-won`"
    (is (= true  (sut/game-won? game-won)))
    (is (= false (sut/game-won? game-lost)))
    (is (= false (sut/game-won? game-new)))
    (is (= false (sut/game-won? game-ongoing)))))

(deftest game-lost
  (testing "`game-lost?`"
    (is (= false (sut/game-lost? game-won)))
    (is (= false (sut/game-lost? game-new)))
    (is (= true  (sut/game-lost? game-lost)))
    (is (= false (sut/game-lost? game-ongoing)))))

(deftest game-over
  (testing "`game-over?`"
    (is (= true  (sut/game-over? game-won)))
    (is (= true  (sut/game-over? game-lost)))
    (is (= false (sut/game-over? game-new)))
    (is (= false (sut/game-over? game-ongoing)))))

(deftest new-game
  (testing "does `new-game` 'tree' return a map
             correctly reflecting this input"
    (is = ((sut/new-game "tree") game-new))))

(deftest hint
  (testing "will `hint` return correct vector for `word-to-guess` `tree`
            and `:correct-guesses` `#{'t'}` "
    (is (= ["t" nil nil nil] (sut/hint game-ongoing) ))))

(deftest score
  (testing "does `score` compute correctly"
    (is (= 0  (sut/score (sut/new-game "tree"))))
    (is (= 24 (sut/score game-won)))))
