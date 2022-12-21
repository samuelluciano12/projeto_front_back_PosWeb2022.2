// studentModal.open = true // funciona para abrir o dialog
// studentModal.open = false// funciona para fechar o dialog
// studentModal.setAttribute('open', true) // funciona para abrir o dialog
// studentModal.setAttribute('open', false) // não funciona para fechar o dialog
// studentModal.removeAttribute('open') funciona para fechar o dialog
// studentModal.showModal() // funciona para abrir o dialog
// studentModal.close() funciona para fechar o dialog

const studentModal = document.querySelector('#student-modal');
const studentForm = document.querySelector('#student-form');
const studentModalTitle = document.querySelector('#student-modal-title')
const saveStudentButton = document.querySelector('#save-student')

// subjectModal.open = true // funciona para abrir o dialog
// subjectModal.open = false// funciona para fechar o dialog
// subjectModal.setAttribute('open', true) // funciona para abrir o dialog
// subjectModal.setAttribute('open', false) // não funciona para fechar o dialog
// subjectModal.removeAttribute('open') funciona para fechar o dialog
// subjectModal.showModal() // funciona para abrir o dialog
// subjectModal.close() funciona para fechar o dialog

const subjectModal = document.querySelector('#subject-modal');
const subjectForm = document.querySelector('#subject-form');
const subjectModalTitle = document.querySelector('#subject-modal-title')
const savesubjectButton = document.querySelector('#save-subject')
/**
 * Função responsável abrir o modal de estudante
 */
const openStudentModal = () => studentModal.showModal();

/**
 * Função responsável fechar o modal de estudante
 */
const closeStudentModal = () => studentModal.close();

/**
 * Função responsável por criar linhas na tabela student-table
 * @param {nome} string
 * @param {matricula} string
 * @param {curso} string
 * @param {id} string
 */
const createStudentTableRow = (nome, matricula, curso, id) => {
  const studentTable = document.querySelector('#student-table tbody')
  const tableTr = document.createElement('tr');
  tableTr.innerHTML = ` 
  <td>${nome}</td>
  <td>${matricula}</td>
  <td>${curso}</td>
  <td align="center">
    <button class="button button--danger" onclick=deleteStudentTable(${id})>Apagar</button>
    <button class="button button--success" onclick="editdStudentModal(${id})">Editar</button>
  </td>`;
  studentTable.appendChild(tableTr);
}

/**
 * Função responsável savar os dados de um estudante
 * @param {url} string
 * @param {method} string
 */
const saveStundentData = (url, method) => {
  studentForm.addEventListener('submit', (event)=> {
    event.preventDefault();
    const formData = new FormData(studentForm);    
    let object = {};
    formData.forEach((value, key) => object[key] = value);
    var json = JSON.stringify(object);
    fetch(url, {  
      headers: {
        'Content-type': 'application/json'
      },             
        method: method,    
        body: json       
    })    
    .catch(error => {
        closeStudentModal();
        alert('ocorreu um erro tente mais tarde')
        console.error(error);        
    }).then(setTimeout("location.reload(true);",200))   
    // const inputs = document.querySelectorAll('input') // pega todos os iputs
    // console.log(inputs[0].value) // acessa o primeiro indice do array de inputs
  });
}


/**
 * Função responsável abrir o modal de aluno e salvar um novo aluno
 * @param {studentId} string
 */
const createStudent = () => {
  openStudentModal();
  studentModalTitle.textContent = 'Novo Aluno';
  saveStudentButton.textContent = 'Criar';
  
  saveStundentData('http://localhost:8080/novoaluno',  'POST');
}

/**
 * Função responsável abrir o modal de edição e carregar os dados de um estudante e salvar os dados da edição
 * @param {studentId} string
 */
 const editdStudentModal = async (studentId)  => {
  const url = `http://localhost:8080/editaraluno/${studentId}`;
  openStudentModal();
  studentModalTitle.textContent='Editar aluno';
  saveStudentButton.textContent = 'Editar';
  const [nome, matricula] = document.querySelectorAll('input');
  
  const selectCurso =  document.querySelector("#curso");
  fetch(url)
  .then(resp => resp.json())
  .then(data => {    
    nome.value = data.nome
    matricula.value = data.matricula
    selectCurso.value =  data.curso
  })
  saveStundentData(url,  'PUT');
 };

/**
 * Função responsável por apagar dados de um estutande
 * @param {studentId} string
 */
const deleteStudentTable = async (studentId)  =>  
  fetch(`http://localhost:8080/alunos/${studentId}`, {method : 'DELETE'})
  .then(setTimeout("location.reload(true);",200))

/**
 * Função responsável por carregar os dados da student-table
 */
const loadStudentTable = () => {
  fetch('http://localhost:8080/alunos')
  
  .then(resp => resp.json())  
  .then(data => {    
    data.forEach(item => {
      createStudentTableRow(item.nome, item.matricula, item.curso, item.id)
    })
  }).catch((error) => {
    alert('ocorreu um erro tente mais tarde')
    console.error(error);
  });
};

loadStudentTable();









/**
 * Função responsável abrir o modal de estudante
 */
const opensubjectModal = () => subjectModal.showModal();

/**
 * Função responsável fechar o modal de estudante
 */
const closesubjectModal = () => subjectModal.close();

/**
 * Função responsável por criar linhas na tabela subject-table
 * @param {nome} string
 * @param {matricula} string
 * @param {curso} string
 * @param {id} string
 */
const createsubjectTableRow = (nome, cargaHoraria, professor,status,observacoes,id) => {
  const subjectTable = document.querySelector('#subject-table div')
  const tableTr = document.createElement('div');
  
  if (status == "Obrigatório") {
    tableTr.innerHTML = ` 
    <div class="subject-card" >
            
  
    <h3 class="subject-card__title">${nome}</h3>
              <hr />
              <ul class="subject-card__list">
                <li>carga horária: ${cargaHoraria}</li>
                <li>Professor: ${professor}</li>
                <li>Status: <span class="tag tag--danger">${status}</span></li>
              </ul>            
    <ul class="subject-card__list">
      <li align="center">
        <button class="button button--danger" onclick=deletesubjectTable(${id})>Apagar</button>
        <button class="button button--success" onclick="editdsubjectModal(${id})">Editar</button>
      </li>
    </ul>
    <p>${observacoes}</p>
    </div>`; 
  } else {

    tableTr.innerHTML = ` 
    <div class="subject-card" >
  <h3 class="subject-card__title">${nome}</h3>
            <hr />
            <ul class="subject-card__list">
              <li>carga horária: ${cargaHoraria}</li>
              <li>Professor: ${professor}</li>
              <li>Status: <span class="tag tag--success">${status}</span></li>
            </ul>            
  <ul class="subject-card__list">
    <li align="center">
      <button class="button button--danger" onclick=deletesubjectTable(${id})>Apagar</button>
      <button class="button button--success" onclick="editdsubjectModal(${id})">Editar</button>
    </li>
  </ul>
  <p>${observacoes}</p>
  </div>`    ;
  }
  
  subjectTable.appendChild(tableTr);
}

/**
 * Função responsável savar os dados de um estudante
 * @param {url} string
 * @param {method} string
 */
const savesubjectData = (url, method) => {
  subjectForm.addEventListener('submit', (event)=> {
    event.preventDefault();
    const formData = new FormData(subjectForm);    
    let object = {};
    formData.forEach((value, key) => object[key] = value);
    var json = JSON.stringify(object);
    fetch(url, {  
      headers: {
        'Content-type': 'application/json'
      },             
        method: method,    
        body: json       
    })    
    .catch(error => {
        closesubjectModal();
        alert(error);
        console.error(error);        
    }).then(setTimeout("location.reload(true);",200))
    // const inputs = document.querySelectorAll('input') // pega todos os iputs
    // console.log(inputs[0].value) // acessa o primeiro indice do array de inputs
  });
}


/**
 * Função responsável abrir o modal de disciplina e salvar um novo disciplina
 * @param {subjectId} string
 */
const createsubject = () => {
  opensubjectModal();
  subjectModalTitle.textContent = 'Nova disciplina';
  savesubjectButton.textContent = 'Criar';
  
  savesubjectData('http://localhost:8080/novodisciplina',  'POST');
}

/**
 * Função responsável abrir o modal de edição e carregar os dados de um estudante e salvar os dados da edição
 * @param {subjectId} string
 */
 const editdsubjectModal = async (subjectId)  => {
  const url = `http://localhost:8080/editardisciplina/${subjectId}`;
  opensubjectModal();
  subjectModalTitle.textContent='Editar disciplina';
  savesubjectButton.textContent = 'Editar';
  const nome = document.querySelector("#nomedisciplina");
  const cargaHoraria = document.querySelector("#cargaHoraria");
  const selectStatus =  document.querySelector("#status");
  const professor = document.querySelector("#professor");
  const observacoes = document.querySelector("#observacoes");
  fetch(url)
  .then(resp => resp.json())
  .then(data => {    
    nome.value = data.nomedisciplina
    professor.value = data.professor
    observacoes.value = data.observacoes
    cargaHoraria.value = data.cargaHoraria
    selectStatus.value =  data.status
  })
  savesubjectData(url,  'PUT');
 };

/**
 * Função responsável por apagar dados de um estutande
 * @param {subjectId} string
 */
const deletesubjectTable = async (subjectId)  =>  
  fetch(`http://localhost:8080/disciplinas/${subjectId}`, {method : 'DELETE'})
  .then(setTimeout("location.reload(true);",200));

/**
 * Função responsável por carregar os dados da subject-table
 */
const loadsubjectTable = () => {
  fetch('http://localhost:8080/disciplinas')
  
  .then(resp => resp.json())  
  .then(data => {    
    data.forEach(item => {
      createsubjectTableRow(item.nomedisciplina, item.cargaHoraria, item.professor, item.status,item.observacoes,item.id)
    })
  }).catch((error) => {
    alert('ocorreu um erro tente mais tarde')
    console.error(error);
  });
};

loadsubjectTable();





