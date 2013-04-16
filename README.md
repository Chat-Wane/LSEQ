LSEQ: CRDT for sequences
========================

Synthetic Documents Experiments
-------------------------------

<table border="1">

<tr> <th rowspan="3"> nbInsert </th>
     <th colspan="4"> FRONT </th>
     <th colspan="4"> QUEUE </th>
     <th colspan="4"> RANDOM </th> </tr>

<tr> <th colspan="2"> id lenght </th>
     <th colspan="2"> id bit-lenght </th>
     <th colspan="2"> id lenght </th>
     <th colspan="2"> id bit-lenght </th>
     <th colspan="2"> id lenght </th>
     <th colspan="2"> id bit-lenght </th> </tr>

<tr> <th> AVG </th> <th> MAX </th> <th> AVG </th> <th> MAX </th>
     <th> AVG </th> <th> MAX </th> <th> AVG </th> <th> MAX </th>
     <th> AVG </th> <th> MAX </th> <th> AVG </th> <th> MAX </th> </tr>

<tr></tr>

<tr>
<td>100</td> <td>19.3</td> <td>39</td> <td>193.3</td> <td>390</td> <td>1</td>
<td>1</td> <td>10</td> <td>10</td> <td>2.43</td> <td>5</td> <td>24.3</td>
<td>50</td>
</tr>

<tr>
<td>1000</td> <td>197.2 </td>  <td>397 </td>  <td>1972.4 </td>   <td>3970 </td>
<td>2.92 </td>  <td>5 </td>  <td>29.2 </td>  <td>50</td>  <td>3.63 </td>  <td>7
</td>  <td>36.3 </td>  <td>70</td>

</tr>
<td>5000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>12.54</td><td>25</td>
<td>125.42</td><td>250</td><td>4.5</td><td>10</td><td>45</td><td>100</td>
</tr>

<tr>
<td>10000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>24.62</td><td>49</td>
<td>246.2</td><td>490</td><td>4.87</td><td>11</td><td>48.7</td><td>110</td>
</tr>

<tr>
<td>50000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>121.7</td><td>243</td>
<td>1217</td><td>2430</td><td>5.73</td><td>13</td><td>57.3</td><td>130</td>
</tr>

<tr>
<td>100000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td>
<td>X</td><td>X</td><td>6.09</td><td>14</td><td>60.8</td><td>140</td>
</tr>

<caption><strong>Table 1</strong>. Boundary setup with a single <i>boundary+</i>
strategy and constant base=2<sup>10</sup>, boundary=10.</caption>
</table>

