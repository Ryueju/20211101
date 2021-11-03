// array5.js
//요소 (태그 )생성 : document. createElement('element')
//상위요소 > 하위요소 : 상위요소.appendChile(하위요소);


function User(id, name, point) { //생성자 함수 // U 대문자는 생성자 함수임을 나타내려고
    this.id = id;
    this.name = name;
    this.point = point;
}
//new라는 키워드로
// new User('user1','포챠코',90);
const users = [new User('user1', '포챠코', 90),
    new User('user2', '시나모롤', 100),
    new User('user3', '쿠로미', 200),
    new User('user4', '마이멜로디', 500)
];

//표형식(table)로 행석
document.write('<button onclick="createContent()">CREATE</button>');
document.write('<div id="show"></div>');
//<ul><li>아이디,이름,점수</li><li>...


function createContent() {
    let tableTag, trTag, tbodyTag, tdTag, theadTag;
    tableTag = document.createElement('table');
    //tbody
    // tableTag.border = "1"; //style
    tableTag.setAttribute('border','1');
    theadTag = document.createElement('thead');
    trTag = document.createElement('tr'); //tr 생성
    theadTag.appendChild(trTag);
    tableTag.appendChild(theadTag);
    for (let user in users[0]) {
        tdTag = document.createElement('th');
        tdTag.innerHTML = user;
        trTag.appendChild(tdTag);
    }
    tbodyTag = document.createElement('tbody');
    for (let i = 0; i < users.length; i++) {

        trTag = document.createElement('tr'); //tr 생성
        for (let user in users[i]) {
            tdTag = document.createElement('td');
            tdTag.innerHTML = users[i][user];
            tdTag.id = user.toLowerCase();
            //tr받고
            trTag.appendChild(tdTag);
        }
        tbodyTag.appendChild(trTag);
        //tbody tr 받고
    }
    tableTag.appendChild(theadTag); //테이블 > t헤드
    tableTag.appendChild(tbodyTag);
    document.getElementById("show").appendChild(tableTag);
}