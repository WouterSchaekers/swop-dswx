# Constraints #
  * Niet meer dan 10 XRays per jaar. -> In use case<p>
<ul><li>Nurses: 8h-17h (elke dag). -> Fixen in canBeScheduledAt en nextFreeSlot. (fixed)<p>
</li><li>Doctors en MedicalTests minstens 1h van de currentTime schedulen. -> In de use case. (Extra methode in scheduler waarbij het uur kan meegegeven worden. (fixed))<p>
</li><li>Back-to-back: Enkel voor één persoon. -> Extra methode in scheduler waarbij één persoon vooraan wordt geplaatst. (fixed)