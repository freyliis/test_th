<ul>
<li>To build: gradle build</li>
<li>To run: gradle run -DsourcePath=input.txt</li>
<li>Input file is available in the project root but sourcePath can take any path (absolute one)</li>
</ul>
Assumptions:
<ul>
<li>Roman Numbers are unchangable. You cannot define new Roman Number in the input</li>
<li>There are two types of text lines to parse:
<ul>
      <li>Definition</li>
      <ul>
            <li>Intergalactic unit definition aka "glob is V"</li>
            <li>Resource definition aka "glob blob Silver is 34 Credits"</li>
      </ul>
      <li>Question</li>
        <ul>
             <li>How much is</li>
             <li>How many Credits is</li>
             <li>Any other</li>
         </ul>
</ul>
</li>
<li>All input is first parsed then questions are answered. Int That case there can be many definitions but valid is the first one.</li>
<li>If the definition matches regex but input is invalid, like:
<ul>
      <li>Invalid Roman Number</li>
      <li>Duplicated Intergalactic Unit definition</li>
      <li>No integalactic unit in the resource definition</li>
</ul>
there is a ParserException thrown and program ends.
</li>
<li>In the resource definition starting strings are parsed to Roman Numbers until there is unknown text. Then the rest is treated as resource name</li>
<li></li>
</ul>