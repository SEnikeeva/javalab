<#include "base.ftlh">

<#macro title>Acceptance</#macro>

<#macro content>
     <div style="padding-top: 10px">
         <br>
         <div align="center">
             <div style="background-color: #e83e8c;">
                 <h1> Application for selection a course </h1>
                 <br>
                 <br>
                 <table>
                     <tr>
                         <td>Name</td>
                         <td>${user.name}</td>
                     </tr>
                     <tr>
                         <td>Surname</td>
                         <td>${user.surname}</td>
                     </tr>
                     <tr>
                         <td>Age</td>
                         <td>${user.age}</td>
                     </tr>
                     <tr>
                         <td>Passport</td>
                         <td>${user.passport}</td>
                     </tr>
                 </table>

                 <h5 >
                     We are glad to announce that you are accepted for the ${course} course.
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