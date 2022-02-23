(ns hangman1.game-test
  (:require [clojure.test :refer :all]
            [game :refer :all]))

;ein verlorenes Spiel (der Spieler konnte das Wort nicht erraten)
(def game-lost
  {:word-to-guess "tree"
   :correct-guesses #{"t"}
   :tries-left 0})

;;ein gewonnenes Spiel (der Spieler konnte das Wort erraten)
(def game-won
  {:word-to-guess "tree"
   :correct-guesses #{"t" "r" "e"}
   :tries-left 0})

;;ein laufendes Spiel
(def game-ongoing;;ein laufendes Spiel
  (def game-ongoing
    {:word-to-guess "tree"
     :correct-guesses 0
     :tries-left 0})
  {:word-to-guess "tree"
   :correct-guesses #{"t"}
   :tries-left 0})

;;ein ganz neues Spiel
(def game-new
  {:word-to-guess "tree"
   :correct-guesses #{}
   :tries-left 5})

(deftest f-game-won
  (testing "game-lost?"
    (is true  (game-won? game-won))
    (is false (game-won? game-lost))
    (is false (game-won? game-ongoing))))

(deftest f-game-lost
  (testing "game-lost?"
    (is false (game-lost? game-won))
    (is  true  (game-lost? game-lost))
    (is  false (game-lost? game-ongoing))))

(deftest f-game-over
  (testing "game-over?")
  (is  false (game-lost? game-won))
  (is  true  (game-lost? game-lost))
  (is  false (game-lost? game-ongoing)))

(deftest f-new-game
  (testing "new-game"
    (is = ((new-game "tree") game-new))))
