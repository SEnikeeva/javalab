<#include "base.ftlh">

<#macro title>Condolences</#macro>

<#macro content>
     <div style="padding-top: 10px">
         <br>
         <div align="center">
             <div style="background-color: #e83e8c;">
                 <h1> Good morning, ${user.name}!</h1>
                 <br>
                 <br>
                 <h5 >
                     We are glad to announce that you are not accepted for the ${course} course.
                 </h5>
                 <br>
                 <h5 style="background-color: #e8da58">
                     and that letter was albert Einstein
                 </h5>

             </div>
         </div>
     </div>
</#macro>

<@main/>