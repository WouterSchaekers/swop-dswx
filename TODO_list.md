# TODOs #
  * **Visual Paradigm:** SSDs van alle use cases
    * Login
    * Logout
    * Register patient
    * Order medical test
    * Add hospital staff
    * Add hospital equipment
    * Prescribe treatment
    * Enter Diagnose
    * Approve Diagnose
    * Discharge patient
    * Enter medical test result
    * Enter medical treatment result
    * Fill stock in warehouse
    * Select location preference
    * Advance time
    * List orders
  * **Implementatie:** Test scenario's
  * **Implementatie:** use cases
  * **Documentatie**
    * Schedulable doc: updateTimeTable dient voor advance time, niet om een Schedulable een nieuwe time table toe te wijzen of iets dergelijks..
    * create dummy date in Nurse
    * ...

# Wie is er momenteel waaraan bezig? #
<p>
Dieter: controllers nalezen<p>
Stefaan: verslag<p>
Wouter: tests maken en hoog niveau verslag<p>

<h1>Things that have been crossed off</h1>
<ul><li>Treatment Factories gemaakt en getest<br>
</li><li>Scheduler-bugje: waar de juiste resource bepaald wordt: checken dat ze nog niet is opgenomen in de lijst van dingen die al gevonden zijn.<br>
</li><li>Commentaar voor TimeTable.<br>
</li><li>Testing for union and intersections<br>
</li><li>union tests have been written.<br>
</li><li>Task and TaskManager have been rewritten.<br>
</li><li>Task has been updated.<br>
</li><li>Requirement-interface has been added; check javadoc for more info.<br>
</li><li>Alles defensief maken en documentatie schrijven en nakijken (behalve scheduler package want daar mocht Stefaan niet aankomen).<br>
</li><li>Scheduler debuggen<br>
</li><li>De dude mailen met een draft versie van het verslag en de vraag of onze scheduler nu wel objectgericht geprogrammeerd is.<br>
</li><li>TEKENINGETJES MAKEN ^^<br>
</li><li>Een klasse maken die gregoriancalendar extend en die gemakkelijke functies heeft om daarop dingen aan te passen en daarna terug te getten. Vervolgens alle Date dingen refactoren.<br>
</li><li>scheduler (yay!)<br>
</li><li>scheduler moet dynamisch gemaakt worden<br>
</li><li>warehouse<br>
</li><li>Result<br>
</li><li>WarehouseAdmin<br>
</li><li>constraints in canBeScheduled<br>
</li><li>Factory patronen implementeren voor MedicalTest<br>
</li><li>eten<br>
</li><li>warehouse<br>
</li><li>warehouseadmin<br>
</li><li>Bij unscheduled classes zorgen dat die requirements lazy gemaakt worden, (dit door de voorstelling van onze requirements :) )<br>
</li><li>Observers<br>
</li><li>Bidirectionele binding tussen TaskManager en Schedulable<br>
</li><li>Mailtje naar de dude sturen voor een afspraak de week voor de deadline en een doodle maken<br>
</li><li>extended deadline aanvragen<br>
</li><li>mplementatie:<b>Stockmanager/warehouse/warehouseadmin<br>
</li><li></b>Implementatie:<b>Requirements en Descriptions<br>
</li><li></b>Implementatie:<b>Result<br>
</li><li></b>Implementatie:<b>Scheduler<br>
</li><li></b>Implementatie:<b>Treatment factories<br>
</li><li>zie de feedbacksessie van iteratie 2<br>
</li><li></b>Design review:<b>use case van advanceTime() moet nog eens nauwkeurig bestudeerd worden.<br>
</li><li></b>Implementatie:<b>Controllers houden IDs bij van de objecten die ze aan de buitenwereld terug geven om zo inconsistenties in het systeem te vermijden.<br>
</li><li></b>Check: