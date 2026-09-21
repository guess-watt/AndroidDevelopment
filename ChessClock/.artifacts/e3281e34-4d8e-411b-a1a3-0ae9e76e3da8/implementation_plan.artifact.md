# Implementation Plan - Add Game Controls, Alerts, and History Storage

The user requested adding functional game controls (Reset, Play/Pause, Stop) with confirmation and outcome dialogs, as well as saving all game records to the database when a game concludes (either by manual stop or timer expiration).

## User Review Required

> [!IMPORTANT]
> - **Reset Button**: Will display a confirmation dialog before resetting the timers to their initial values.
> - **Play/Pause Button**: Will pause or resume the running timer and toggle its text between "Pause" and "Resume".
> - **Stop Button**: Will pause the game and open a dialog asking who won ("Player 1", "Player 2", or "Draw"). Selecting an option will save the record and return to the main screen.
> - **Timer Expiration**: When a timer finishes, the game will automatically save the result with the opposite player as the winner.

## Proposed Changes

### Game Screen Logic

#### [MODIFY] [ChessClockActivity.java](file:///C:/Android Development/ChessClock/app/src/main/java/com/example/chessclock/ChessClockActivity.java)
- Initialize the layout buttons (`resetButton`, `playPauseButton`, `stopButton`).
- Implement the click listener for `resetButton` to show an `AlertDialog` confirmation.
- Implement the click listener for `playPauseButton` to toggle pause/resume state and update button text dynamically.
- Implement the click listener for `stopButton` to present an `AlertDialog` choice of the winner ("Player 1 Name", "Player 2 Name", or "Draw").
- Capture the `startTime` when the game begins.
- Implement a helper method `saveGameRecord(String winner, String resultType)` that calculates total duration, final remaining times, and invokes `DatabaseHelper.insertGame()`.
- Update `onFinish()` of both `CountDownTimer` instances to automatically log the game record with `resultType = "Timeout"`.

## Verification Plan

### Automated Tests
- Build the project using `gradle assembleDebug` to verify no compilation errors occur.

### Manual Verification
1. **Play/Pause Integration**: Tap the center button during a game to ensure timers stop and text changes to "Resume". Tap again to resume.
2. **Reset Dialog**: Tap "Reset", select "Cancel" (timer continues), tap "Reset" again, select "Yes" (timers reset to full).
3. **Stop & Choose Winner**: Tap "Stop", choose a winner from the dialog, and verify it saves and returns home.
4. **Timer Expiration**: Let a timer run down to 0, verify the win toast appears, and check that the record is auto-saved.
5. **View History**: Navigate to "Game History" from the main screen to confirm the saved games appear with correct details.
