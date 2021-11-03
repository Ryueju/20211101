//func4.js

const persons = [{
    name:'포챠코',
    eng: 80,
    math:100,
    grade:'A'
    
},{
    name:'시나모롤',
    eng:90,
    math:60,
    grade:'B'
},{
    name:'쿠로미',
    eng:100,
    math:100,
    grade:'A+'
}];
persons[0]['math'] =90; //배열 몇번째 데이터의 프로퍼티

function makeTr(obj){
    //<tr><td>obj.name</td><td>obj.eng</td><td>obj.math</td></tr>
    // let tr = '<tr><td>'+obj.name+'</td><td>'+obj.eng+'</td><td>'+obj.math+'</td></tr>';
    let tr = '<tr>';
    for(let field in obj){// object 경우 for in
        tr += '<td>'+obj[field]+'</td>';     } 
    tr+='</tr>';
    return tr;
}


function makeHead(obj){
    let tr = '<tr>';
    for(let field in obj){
        tr +='<th>'+field+'</th>';     }
    tr +='</tr>';
    return tr;
}

let str ='<table border=1><tbody>';
str+=makeHead(persons[0]);
for(let i=0; i<persons.length;i++){
    str += makeTr(persons[i]);
}
str+='</tbody></table>';
document.write(str);

