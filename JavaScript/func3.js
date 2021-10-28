//func3.js

function multiply(num) {
    document.write('<table border=1><tbody>');
    for (let i = 1; i <= 9; i++) {
        document.write('<tr> <td>' + num + '</td> <td>  *</td><td>' + i + '</td><td>= </td> <td>' + num * i + '</td></tr>');
    }//실행하겟다 호출
    document.write('</tbody></table>')
}
multiply(3);
//document.write(mutiplyWithReturn(6));


function multiplyWithReturn(num) {
    //구구단 3단을 document영역에 표시

    
    //여기 복습 안하면 죽음뿐
    let str = '<table border="1">';
    for (let i = 1; i <= 9; i++) { 
        str += '<tr> <td>' + num + '</td> <td>  *</td><td>' + i + '</td><td>= </td> <td>' + num * i + '</td></tr>';
    }
    str +='</table>';
    return str; //<table><tr></tr></table>
}
let result = multiplyWithReturn(6);
document.write(result);

