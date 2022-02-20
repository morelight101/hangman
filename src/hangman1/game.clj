(ns game
  (:require [clojure.string :refer [split]]))

(def ;;^{:private true} ;;not sure whether this is needed, so far only the functions
     ;; were not allowed to access global variables, but global still could exist
  gamedata
 
   {:word-to-be-guessed  ""       ; Eine Textzeichenkette
    :tries-left          5       ; Eine Ganzzahl
    :correct-guesses     #{}     ; Eine Menge von Buchstaben (jeder Buchstabe als String, wenn das einfacher ist)
    }
   
)


  ;; {:global
  ;;  {:word-population ["Palm" "Tree" "guide"]
  ;;   :allowed-letters "abcdefghijklmnopqrstuvwxyzäöüßABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ"
  ;;   :Points-value 8
  ;;   :max-guesses 5}
(def gamedata
  {:word-to-be-guessed ""
   :correct-guesses 0
   :tries-left 5}
  )


(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game `gewonnen hat, andernfalls false. "
  [{:keys [word-to-be-guessed correct-guesses]} ]
  (= word-to-be-guessed correct-guesses))


(defn game-lost?
  "returns true if player lost, otherwise false"
  [{:keys [tries-left]}]
  (zero? tries-left))


(defn game-over?
  "returns true if user lost or won the game, otherwise false"
  [gamedata]
  (or (game-won? gamedata) (game-lost? gamedata)))


(defn new-game
  "creates a new game map, word to be guessed is set to '' to keep the function pure"
  [gamedata]
  (assoc gamedata
         :word-to-be-guessed ""
         :correct-guesses 0
         :tries-left 5)
)


;; testing
;; git






















