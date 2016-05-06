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
1.	loadData：基本上跟lab的相同，只是要能load 7個file
2.	按下1~7時，要接換到相對應的file：使用loadData
3.	將每個點都顯示在螢幕的左側：在 load data的時候就告訴每個character他是第i個被load進來的，用i來判斷初始位子
4.	畫出中間的大圓
5.	判斷滑鼠是否有在點上
6.	顯示點的名稱
7.	Mouse press，將點點拖曳
8.	如果在拖曳的過程中，點點在圓內部，圓要變粗
9.	如果mouse release，點點在圓內，要將點點顯示在圓上
10.	畫線：我們是用貝茲曲線來畫的
11.	遇到的問題：
(1)	如果在network裡面建立一個arraylist儲存在圓上的點，在將點點拖曳到圓上時，會有機率有沒畫好的狀況產生，在addall時，也會有機率性runtime error因此我們改成將”點點是否在圓上”存在每個character裡面，來避免掉這樣的問題
(2)	顏色……
(3)	Buttom…..(我忘了)
(4)	Text……(我忘了)
(5)	如果buttom一直迅速交錯點的話，也會有沒畫好的狀況產生，所以不能點太快
