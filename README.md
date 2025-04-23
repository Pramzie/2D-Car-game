

#*2D-Car-Game*

A simple car racing game built using **Java Swing**. Dodge obstacles, rack up your score, and test your reflexes!

## 📷 Preview:

> A top-down view arcade-style car racing game where a red car avoids black obstacles falling from the top. The road, lane markers, and score display create an immersive feel.

---

## 🎮 How to Play

- Use the **Left Arrow (←)** and **Right Arrow (→)** keys to move your car within the road.
- Avoid the black obstacles falling from the top of the screen.
- Each obstacle successfully dodged earns you **+10 points**.
- Collision ends the game — press **Enter** to restart.

---

## 🧠 Game Logic

- The game window is **500x600 pixels**.
- The car and obstacles are rectangular shapes (`Rectangle` objects).
- Obstacles fall at a fixed speed, and their x-position is randomized.
- Collision detection is handled via the `intersects()` method.
- The score is displayed at the top-left corner.
- On game over, a message and a restart prompt appear.

---

## 🚀 How to Run

1. Make sure you have **Java installed** on your system.
2. Save the code in a file named: `CarRacingGame.java`
3. Compile and run:

```bash
javac CarRacingGame.java
java CarRacingGame
```

---

## 🛠️ Dependencies

- **Java SE** (no external libraries needed)
- Uses:
  - `javax.swing.*` for GUI components
  - `java.awt.*` for drawing and events
  - `java.util.Random` for obstacle generation

---

## 📁 Project Structure

```
CarRacingGame.java
```

Everything is implemented in a single class `CarRacingGame`, which extends `JPanel` and handles:

- Drawing components
- Movement logic
- Key events
- Game loop using `Timer`

---

## ✅ Features

- Smooth animation with `javax.swing.Timer`
- Real-time collision detection
- Score tracking
- Restart game on key press
- Clean and simple user interface

---

## 🎯 Future Improvements

- Add multiple lanes and more cars
- Increase difficulty over time
- Add sound effects and background music
- Include a high-score tracker
- Mobile version using a different UI framework

---

## 📜 License

This project is open-source and free to use for learning or modification.
