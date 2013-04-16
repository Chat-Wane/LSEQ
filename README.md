LSEQ: CRDT for sequences
========================

This framework allows the developper to configure underlying components of
its allocation strategy and to measure the size of identifiers generated
(see [package module](/src/main/java/alma/fr/modules/)). Available components are
divided in 4 categories:
*    Base: Double or Constant (parameter _departure base_)
*    Boundary: Double or Constant (parameter _departure boundary_)
*    Allocation strategies: Beginning (_boundary+_) or Ending (_boundary-_) (parameters _base_, _boundary_)
*    Strategy Choice: Single, Round-Robin or Random (parameter(s) _allocation strategie(s)_)

Of course, the framework also allows to develop custom components.

Run
---
The frameworks gives two examples of launching:
*    On synthetic sequences (Front, Queue or Random)
*    On Wikipedia pages 

Once again, custom synthetic sequences can be added, and other kinds of real
collaborative edited documents complying with the provided interface can be
added.

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
<td>100</td><td>20.26</td><td>41</td><td>365.26</td><td>1025</td><td>3.19</td>
<td>4</td><td>19.91</td><td>26</td><td>2.01</td><td>5</td><td>11.38</td>
<td>35</td>
</tr>

<tr>
<td>1000</td><td>197.47</td><td>404</td><td>27336.5</td><td>83426</td>
<td>6.35</td><td>8</td><td>49.78</td><td>68</td><td>3.24</td><td>7</td>
<td>20.44</td><td>56</td>
</tr>

<tr>
<td>5000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>8.69</td><td>10</td>
<td>77.9</td><td>95</td><td>4.04</td><td>9</td><td>27.1</td><td>81</td>
</tr>

<tr>
<td>10000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>9.69</td><td>11</td>
<td>91.64</td><td>110</td><td>4.4</td><td>10</td><td>30.4</td><td>95</td>
</tr>

<tr>
<td>50000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>11.96</td><td>13</td>
<td>126.26</td><td>143</td><td>5.24</td><td>13</td><td>38.4</td><td>143</td>
</tr>

<tr>
<td>100000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>12.97</td><td>14</td>
<td>143.3</td><td>161</td><td>5.6</td><td>13</td><td>42</td><td>143</td>
</tr>

<tr>
<td>500000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>15.4</td><td>17</td>
<td>186.67</td><td>221</td><td>6.42</td><td>15</td><td>50.9</td><td>180</td>
</tr>

<caption><strong>Table 2</strong>. Doubled base setup experiment. Parameters are
<i>boundary+</i> strategy and base=2<sup>4+depth</sup>, boundary=10.</caption>
</table>



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
<td>100</td><td>1.97</td><td>2</td><td>19.7</td><td>20</td><td>1</td><td>1</td>
<td>10</td><td>10</td><td>2.2</td><td>4</td><td>22.1</td><td>40</td>
</tr>

<tr>
<td>1000</td><td>5.93</td><td>10</td><td>59.3</td><td>100</td><td>4.83</td>
<td>9</td><td>48.3</td><td>90</td><td>3.51</td><td>7</td><td>34.1</td>
<td>70</td>
</tr>

<tr>
<td>5000</td><td>25.42</td><td>50</td><td>254.2</td><td>500</td><td>23.6</td>
<td>47</td><td>235.7</td><td>470</td><td>4.25</td><td>9</td><td>42.5</td>
<td>90</td>
</tr>

<tr>
<td>10000</td><td>49.39</td><td>98</td><td>494</td><td>980</td><td>47.5</td>
<td>95</td><td>475.4</td><td>950</td><td>4.62</td><td>10</td><td>46.2</td>
<td>100</td>
</tr>

<tr>
<td>50000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td>
<td>X</td><td>X</td><td>5.46</td><td>11</td><td>54.6</td><td>110</td>
</tr>

<tr>
<td>100000</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td><td>X</td>
<td>X</td><td>X</td><td>5.82</td><td>13</td><td>58.2</td><td>130</td>
</tr>

<caption><strong>Table 3</strong>. Round-Robin setup experiment. Both
<i>boundary+</i> and <i>boundary-</i> are employed with constant
base=2<sup>10</sup> and boundary=10.</caption>
</table>



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
<td>100</td><td>3.25</td><td>4</td><td>20.4</td><td>26</td><td>4.05</td>
<td>7</td><td>27.73</td><td>56</td><td>2.16</td><td>4</td><td>12.4</td>
<td>26</td>
</tr>

<tr>
<td>1000</td><td>7.77</td><td>9</td><td>67.1</td><td>81</td><td>7.72</td>
<td>9</td><td>65.93</td><td>81</td><td>3.35</td><td>7</td><td>21.3</td>
<td>56</td>
</tr>

<tr>
<td>5000</td><td>9.36</td><td>10</td><td>86.6</td><td>95</td><td>11.04</td>
<td>13</td><td>113.5</td><td>143</td><td>4.13</td><td>10</td><td>28</td>
<td>95</td>
</tr>

<tr>
<td>10000</td><td>10.62</td><td>12</td><td>105.5</td><td>126</td><td>12.02</td>
<td>13</td><td>128.3</td><td>143</td><td>4.5</td><td>10</td><td>31.23</td>
<td>95</td>
</tr>

<tr>
<td>50000</td><td>12.46</td><td>14</td><td>134.5</td><td>161</td><td>14.11</td>
<td>16</td><td>164.9</td><td>200</td><td>5.35</td><td>11</td><td>39.47</td>
<td>110</td>
</tr>

<tr>
<td>100000</td><td>13.26</td><td>15</td><td>148.3</td><td>180</td><td>15.06</td>
<td>16</td><td>182.5</td><td>200</td><td>5.71</td><td>12</td><td>43.2</td>
<td>126</td>
</tr>

<tr>
<td>500000</td><td>15.42</td><td>17</td><td>189.3</td><td>221</td><td>16.3</td>
<td>17</td><td>207.5</td><td>221</td><td>6.54</td><td>14</td><td>52.2</td>
<td>161</td>
</tr>

<tr>
<td>1M</td><td>16.38</td><td>18</td><td>208.9</td><td>243</td><td>17</td>
<td>18</td><td>221.8</td><td>243</td><td>6.9</td><td>15</td><td>56.31</td>
<td>180</td>
</tr>

<caption><strong>Table 4</strong>. LSEQ setup experiment. Random strategy choice
with a doubled base. Strategies : <i>boundary+</i> and <i>boundary-</i> with
base=2<sup>4+depth</sup>, boundary=10.</caption>
</table>




