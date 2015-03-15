# Domain protection #
## Probleemstelling ##
Hoe kunnen we een consistent domain behouden als er interactie gebeurt tuseen de user en objecten?

## DomainObject class ##
A [DomainObject](DomainObject.md) object is a holder for any object in the domain, it is passed out through the [Controller](Controller.md)s and is managed by the [DomainObjectManager](DomainObjectManager.md). The objects passed out by the controller are unique for each controller. This would mean that a [DomainObject](DomainObject.md) passed out by a [Controller](Controller.md) can only be given back to that controller.

## Class Interfaces ##
We want to avoid that objects returned from the controllers have setters so our solution is to write interfaces for all objects that are returned from our domain.

An example would be the following piece of code :
```
interface IAppointment
{
    IDoctor  getDoctor();
    IDate    getStartDate(); 
    IDate    getEndDate(); 
}

class Appointment implements IAppointment
{
    Doctor doctor;
    Date startDate;
    Date endDate;
    @Override
    public IDoctor getDoctor()
        return doctor;
    }
    @Override
    public IDate getStartDate()
        return startDate;
    }
    // rest omitted for brevity
    
}
```

## Oplossingsopties ##
We zien momenteel een 3-tal opties om dit probleem op te lossen.
<br>
<h3>Optie 1: DTOs maken voor alle klasses die we teruggeven</h3>
<br><b>Voordelen:</b> extreem veilig<br>
<br><b>Nadelen:</b> DTOs hebben minder functionaliteit als oorspr objecten, minder inutitief, moeilijker mee te werken, verdubbeling van workload en # aantal klasses<br>
<br>
<h3>Optie 2: Clone & deep copy maken</h3>
<br><b>Voordelen:</b> veilig<br>
<br><b>Nadelen:</b> niet na te gaan of de inkomende kloons inconsistent zijn of niet<br>
<br>	(vb: bestaat de patientfile die je terugkrijgt?)<br>
<br>
<h3>Optie 3: read only objecten teruggeven</h3>
<br><b>Voordelen:</b> veilig, alle functionaliteiten blijven behouden, zekerheid over consistentie,  objecten die bij de controller binnenkomen zijn sowieso niet aangepast<br>
<br><b>Nadelen:</b> opletten bij het implementeren dat er geen onregelmatigheden gebeuren (Dingen die triviaal lijken moeten extensief getest worden.), niet vergeten: dingen zoals PFM.get(PF).enterDiag(...)