(ns game
  (:require [clojure.string :refer [split]]))

;; @Nico: Das Repro ist ein bisschen unaufgeräumt.

  ;; {:global
  ;;  {:word-population ["Palm" "Tree" "guide"]
  ;;   :allowed-letters "abcdefghijklmnopqrstuvwxyzäöüßABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ"
  ;;   :Points-value 8
  ;;   :max-guesses 5}


(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game `gewonnen hat, andernfalls false. "
  [{:keys [word-to-be-guessed correct-guesses]} ]
  (= (into #{} word-to-be-guessed) correct-guesses))


(defn game-lost?
  "returns true if player lost, otherwise false"
  [{:keys [tries-left]}]
  (zero? tries-left))


(defn game-over?
  "returns true if user lost or won the game, otherwise false"
  [game]
  (or (game-won? game) (game-lost? game)))


;; @Nico: Die Funktion ist nicht pure. Du möchtest als Programmierer, der diese Schicht nutzt, ein neues Spiel anlegen können. Wie kann das pure gehen?

(defn new-game!
  "creates a new game map"
  []
  {
   :word-to-guess (nth ["Palm" "bank" "Tree"](rand-int 3)) 
   :correct-guesses '#{}
   :tries-left 5
   }
  )




















