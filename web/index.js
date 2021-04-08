let inputTeam = document.getElementById("nb-team");
const MAXTEAMS = 8;
let nbteam = inputTeam.value;
let newNbTeam = inputTeam.value;
let i = 1;
let form;
let colors = ["blue","red","yellow","green","pink","black","white","purple"];
function displayForms(){
    for(i=1; i<= MAXTEAMS; i++){
        form = document.querySelector(".form"+i);
        if(i<=newNbTeam){
            form.style.display = "flex";
        } else {
            form.style.display = "none";
        }
    }
}
displayForms();
inputTeam.addEventListener("input",function(){
    newNbTeam = inputTeam.value;
    displayForms();
});
let selects = document.getElementsByTagName("select");
selects = Array.from(selects);
let selectsArray = Array(8); // Ce tableau va contenir les valeurs pour chaque équipe
selects.forEach(select => {
    // On commence par actualiser le tableau des couleurs
    selectsArray[select.id.slice(-1)-1] = select.value;
    if (colors.indexOf(select.value)>-1){
        colors.splice(colors.indexOf(select.value),1); // On retire la valeur ajouté
    }
    
    select.addEventListener("input",function(){
        let oldValue = selectsArray[this.id.slice(-1)-1]
        let newValue = this.value;
        if (oldValue !== newValue){
            selectsArray[this.id.slice(-1)-1] = newValue;
           
            colors.splice(colors.indexOf(newValue),1);
            colors.push(oldValue);

            addColor(oldValue);

            removeColor(newValue);
        }
    });
});

function addColor(oldValue) {
    let oldColorClassArray = document.getElementsByClassName(oldValue);
    oldColorClassArray=Array.from(oldColorClassArray);
    oldColorClassArray.forEach(oldColorClass => {
        oldColorClass.style.display = "block";
    });
}
function removeColor(newValue){
    let newColorClassArray = document.getElementsByClassName(newValue);
    newColorClassArray = Array.from(newColorClassArray);
    newColorClassArray.forEach(newColorClass => {
        newColorClass.style.display = "none";
    });
}