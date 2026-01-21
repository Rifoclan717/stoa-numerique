const newNoteInputButton = document.getElementById("newNoteButton");
const newNoteInput = document.getElementById("newNoteInput");
const titleInput = document.getElementById("titleInput");
const notesDivContainer = document.getElementById("notesDivContainer");


// l'EVENT DELEGATION
notesDivContainer.addEventListener("click",async (e) => {
                            // pas un string mais un DOMTokenList donc obliger de contains, on peut pas comparer
                            // y'a également matches
    if(e.target.classList.contains("modifyNotesButtons") || e.target.classList.contains("deleteNotesButtons") ) { // on vérifie que ce qui a déclenché l'event c'est bien un bouton 
        
        const noteDiv = e.target.closest(".noteContainerClass") // remonte jusqu'au parent qui valide le paramètre SELECTEUR (donc il faut mettre . ou #) ici .noteContainerClass

        const noteId = noteDiv.dataset.noteId; // on récupère dans une variable l'id de la note
        
        if(e.target.classList.contains("modifyNotesButtons")) {
            
            //TODO 000000000000 REMPLACCER PLUS TARD AVEC QUERY SELECTOR plutot que tagName 999999999999999

            let noteValue = noteDiv.getElementsByTagName("textarea").item(0).value;
            let titleValue = noteDiv.getElementsByTagName("input").item(0).value;


            modifyNote(noteId,titleValue,noteValue);

            deleteNote(noteId);
  
            
        }
    }
})

newNoteInputButton.addEventListener("click", async (ev) => {

    noteValue = newNoteInput.value;
    titleValue = titleInput.value;

    if(noteValue.trim() === "" || titleValue.trim() === "") {
        window.alert("Vous ne pouvez pas créer de note vide !");
    } else {
        try {
            const response = await fetch("http://localhost:8080/add", {
                method : "POST",
                headers : { "Content-Type": "application/json"
                },
                body : JSON.stringify({
                        titre: titleValue,
                        contenu : noteValue
                })
            })
            main();

            if(!response.ok){
                throw new Error("Erreur serveur");
            }

        } catch (error) {
            console.error(error);
        }
        
    }
})


async function deleteNote(noteID) {
     try {
            const response = await fetch(`http://localhost:8080/${noteID}`, {
                method : "DELETE",
                headers : { "Content-Type": "application/json"
                }
            })
             main();
            if(!response.ok){
                throw new Error("Erreur serveur");
            }     
         
        } catch (error) {
            console.error(error);
            return;
        }
}

async function modifyNote(noteID,titleValue,noteValue) {
    try {
            const response = await fetch(`http://localhost:8080/${noteID}`, {
                method : "PUT",
                headers : { "Content-Type": "application/json"
                },
                body : JSON.stringify({
                        titre: titleValue,
                        contenu : noteValue                   
                })
                
            })
            main();
            if(!response.ok){
                throw new Error("Erreur serveur");
            }
        } catch (error) {
            console.error(error);
        }
}


async function getNotesData() {
    
    try {
            const response = await fetch("http://localhost:8080/", {
                method : "GET",
                headers : { "Content-Type": "application/json"
                }
            })
            if(!response.ok){
                throw new Error("Erreur serveur");
            }
                const data = await response.json(); 
               
                return data;
        } catch (error) {
            console.error(error);
            return;
        }

}

//recrée tout le dom
function renderNotes(data) {
    notesDivContainer.innerHTML = ""; // dans le cas d'un appel de rendernotes, on supprimer les doublons, avant de tout recreer, pour imiter le F5, qui détruit le DOM
    // si one ne faisais pas ca, on aurait eu des doublons car rendernotes, recree par dessus ce qui a été fait sans avoir supprimer au préalablalemme

    data.forEach(element => {

        let container = document.createElement("div");
        container.classList.add("noteContainerClass")
        container.dataset.noteId = element["id"]

        //let titleSpan = document.createElement("span");
       // titleSpan.value = element["titre"]

        let titleInput = document.createElement("input")
        titleInput.type = "text"
        titleInput.maxLength = 100
        titleInput.value = element["titre"];
        titleInput.classList.add("title-input")

        let br1 = document.createElement("br")

        let textArea = document.createElement("textarea")
        textArea.textContent = element["contenu"];
        textArea.classList.add("large-input")

        let br2 = document.createElement("br")
        let buttonModify = document.createElement("button")
        buttonModify.classList.add("modifyNotesButtons")
        buttonModify.textContent = "Modifier la Note"

        let buttonDelete = document.createElement("button")
        buttonDelete.classList.add("deleteNotesButtons")
        buttonDelete.textContent = "Supprimer la Note"

       // container.appendChild(titleSpan);
        container.appendChild(titleInput);
        container.appendChild(br1);
        container.appendChild(textArea);
        container.appendChild(br2);
        container.appendChild(buttonModify);
        container.appendChild(buttonDelete);
        notesDivContainer.appendChild(container);
    });
}


async function main() {
    //une async renvoie toujours une promesse
    let notesData = await getNotesData();
    renderNotes(notesData);
}

main()