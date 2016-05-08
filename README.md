# Software Studio Assignment 6

## Notes
+ You will have to import the libraries on your own. All the libraries are provided on iLMS.
+ Even you followed the design TA shown in the video, you still have to explain the control of the program in the next section.

## Explanation of the Design
Explain your design in this section.  
Example:
### Operation
+ Clicking on the button "Add All": users can add all the characters into network to be analyzed.
+ Clicking on the button "Clear": users can remove all the characters from network.
+ Hovering on the character: the name of the character will be revealed.
+ By dragging the little circle and drop it in the big circle, the character will be added to the network.
+ By pressing key 1~7 on the keyboard, users can switch between episodes.
+ ...etc.

### Visualization
+ The width of each link is visualized based on the value of the link.
+ ...etc.
1.	loadData：基本上跟lab的相同，只是要能load 7個file，根據keypressed來判斷
2.	按下1~7時，要接換到相對應的file：使用loadData
3.	將每個點都顯示在螢幕的左側：在 load data的時候就告訴每個character他是第i個被load進來的，用i來判斷初始位子，利用i/10跟i%10就可以簡單排列
4.	畫出中間的大圓
5.	判斷滑鼠是否有在點上，利用mouseX跟mouseY來檢查是否有在圓圈內
6.	顯示點的名稱
7.	Mouse press，將點點拖曳(利用curCh的更新來但斷線在抓到哪個點點)
8.	如果在拖曳的過程中，點點在圓內部，圓要變粗
9.	如果mouse release，點點在圓內，要將點點顯示在圓上，在圓外面就要放回去並檢查點點是否已經從圓圈中除名
10.	畫線：我們是用貝茲曲線來畫的，頭尾是source跟target，控制點為大圓圓心
11. 圈圈排列：每個點點進到大圈圈時，都會先判斷現在有多少點點已經在大圓上，然後均分大圓的周長，利用三角函數可以很簡單做到排列效果	
12.	遇到的問題：
+	如果在network裡面建立一個arraylist儲存在圓上的點，在將點點拖曳到圓上時，會有機率有沒畫好的狀況產生，在addall時，也會有機率性runtime error因此我們改成將”點點是否在圓上的一個 private int”存在每個character裡面，來避免掉這樣的問題
+	顏色解碼是將16進位轉成10進位
+	Buttom原本是用Button來做，但是發現無法完美控制點點的移動或是造成runtime error，所以改成 processing 裡面的一個button的功能，就可以比較簡單控制點點
+	Text原本是分別放在characters跟main applet裡面，因為都用到text()的method，有出現說我顯示characters的名字，結果title就消失了，所以最後決定是放在mainApplet裡面同時做控制，才避免掉這個問題
+	如果buttom一直迅速交錯點的話，也會有沒畫好的狀況產生，所以不能點太快，因為點點還沒移動到定位，會造成程式上判斷的失誤
