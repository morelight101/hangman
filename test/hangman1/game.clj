(ns hangman1.game-test
  (:require [clojure.test :refer :all]
            [hangman1.game :refer :all]))
;ein verlorenes Spiel (der Spieler konnte das Wort nicht erraten)
(def game-lost
 { :word-to-be-guessed ""
   :correct-guesses 0
   :tries-left 0}
  )

;;ein gewonnenes Spiel (der Spieler konnte das Wort erraten)
(def game-won
 { :word-to-be-guessed ""
   :correct-guesses 0
   :tries-left 0}
  )

;;ein laufendes Spiel
(def game-ongoing
 { :word-to-be-guessed ""
   :correct-guesses 0
   :tries-left 0}
  )

(deftest game-won?
  (testing "game-lost?"
    (=  true  (game-won? game-won ))
    (=  false (game-won? game-lost ))
    (=  false (game-won? game-ongoing ))
    )


(deftest game-lost?
  (testing "game-lost?"
    (=  false (game-lost? game-won ))
    (=  true  (game-lost? game-lost ))
    (=  false (game-lost? game-ongoing ))
    )


(deftest game-over?
  (testing "")
    (=  false (game-lost? game-won ))
    (=  true  (game-lost? game-lost ))
  (=  false (game-lost? game-ongoing ))




(deftest new-game
  "creates a new game map, word to be guessed is set to '' to keep the function pure"
  [gamedata]
  (assoc gamedata
         :word-to-be-guessed ""
         :correct-guesses 0
         :tries-left 5))


