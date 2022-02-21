(ns game
  (:require [clojure.string :refer [split]]))

(defn game-won?
  "Liefert true, wenn der Spieler das Spiel `game `gewonnen hat, andernfalls false. "
  [{:keys [word-to-be-guessed correct-guesses]}]
  (= (into #{} word-to-be-guessed) correct-guesses))

(defn game-lost?
  "returns true if player lost, otherwise false"
  [{:keys [tries-left]}]
  (zero? tries-left))

(defn game-over?
  "returns true if user lost or won the game, otherwise false"
  [game]
  (or (game-won? game) (game-lost? game)))

(defn new-game
  "creates a new game map"
  [word-to-guess]
  {:word-to-guess  word-to-guess
   :correct-guesses '#{}
   :tries-left 5})

(defn guess-letter
  "returns an updated game reflecting whether the letter the user guessed was contained in the word-to-guess"
  [user-input game]
  (if (contains?  (set (map  str (seq (:word-to-guess game)))) user-input)
    (update-in game [:correct-guesses] conj user-input)
    (update-in game [:tries-left] dec)))
