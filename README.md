# GuessGame

## 專案簡介
此專案是一個數字猜測遊戲，玩家需在有限的次數內猜中目標數字。本專案依據指定規格進行開發，但在部分實現上進行了一些調整，以提供更好的使用體驗。

## 實現過程中的調整
雖然根據老師制定的規格，應該回傳預定的資料型態，但為了提升使用者體驗(?)，所以修改了回傳型態，更方便處理錯誤信息並跳出提示，並不會影響到核心邏輯的正確性。

## 調整細節
1. **回傳型態變更**：
    - 原本老師要求 "guess" method 回傳的型態為 boolean，實際回傳了一個自訂的型態：GuessNumberResult
   
2. **UML 圖**：
    以下附上修改後的 UML 圖
    - 老師規定的結構
      ![upload_67d367e1e2b222c50e09e3f77824103b](https://github.com/user-attachments/assets/2d6be59d-45b0-494a-88d7-49ae02ea923a)
    - 我實作上更改過的結構
      ![upload_4ec0b05074ae0b7262b8253509ab8021](https://github.com/user-attachments/assets/daf6eedc-7289-41eb-8481-d9449bcf7702)


