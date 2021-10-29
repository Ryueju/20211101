// array4.js


document.write('<button onclick="createList()">생성</button>');
document.write('<div id = "show"></div>');

function createList(){
    let fruits = ['Apple','Banana','Cherry'];
    let ulTag,liTag;

    ulTag = document.createElement('ul'); //<ul></ul>
    
    // document.createElement(' ul'); //태그를 만드는 메소드
    for(let fruit of fruits){

        liTag = document.createElement('li'); //<li></li>
        liTag.innerHTML =fruit; //<li>Apple</li>
        liTag.id = fruit.toLowerCase();
        ulTag.appendChild(liTag);//<ul><li>Apple</li></ul>
    }
   
    // liTag = document.createElement('li');
    // liTag.innerHTML = 'Banana';
    // liTag.id = 'banana';
    // ulTag.appendChild(liTag);
    // console.log(ulTag);

   document.getElementById("show").appendChild(ulTag);
    
}
const data = 
[
    {
        "id": 1,
        "first_name": "Adrian",
        "last_name": "Burhouse",
        "email": "aburhouse0@cargocollective.com",
        "gender": "Male",
        "ip_address": "10.144.80.120"
    },
    {
        "id": 2,
        "first_name": "Archambault",
        "last_name": "Lightwood",
        "email": "alightwood1@ezinearticles.com",
        "gender": "Polygender",
        "ip_address": "128.93.170.75"
    },
    {
        "id": 3,
        "first_name": "Sada",
        "last_name": "Sleightholm",
        "email": "ssleightholm2@wordpress.org",
        "gender": "Agender",
        "ip_address": "250.120.252.247"
    },
    {
        "id": 4,
        "first_name": "Allx",
        "last_name": "Mill",
        "email": "amill3@i2i.jp",
        "gender": "Non-binary",
        "ip_address": "18.108.97.169"
    },
    {
        "id": 5,
        "first_name": "Joline",
        "last_name": "Maysor",
        "email": "jmaysor4@xrea.com",
        "gender": "Bigender",
        "ip_address": "213.69.250.251"
    },
    {
        "id": 6,
        "first_name": "Adelle",
        "last_name": "Dotson",
        "email": "adotson5@bing.com",
        "gender": "Genderqueer",
        "ip_address": "197.50.101.29"
    },
    {
        "id": 7,
        "first_name": "Keen",
        "last_name": "Ackroyd",
        "email": "kackroyd6@businesswire.com",
        "gender": "Bigender",
        "ip_address": "246.93.240.248"
    },
    {
        "id": 8,
        "first_name": "Milena",
        "last_name": "Gregorace",
        "email": "mgregorace7@vinaora.com",
        "gender": "Bigender",
        "ip_address": "243.169.189.228"
    },
    {
        "id": 9,
        "first_name": "Tommie",
        "last_name": "Mounce",
        "email": "tmounce8@last.fm",
        "gender": "Non-binary",
        "ip_address": "3.232.126.183"
    },
    {
        "id": 10,
        "first_name": "Dennie",
        "last_name": "Readitt",
        "email": "dreaditt9@examiner.com",
        "gender": "Genderqueer",
        "ip_address": "78.215.206.87"
    },
    {
        "id": 11,
        "first_name": "Buckie",
        "last_name": "Leas",
        "email": "bleasa@diigo.com",
        "gender": "Female",
        "ip_address": "118.122.175.112"
    },
    {
        "id": 12,
        "first_name": "Allyson",
        "last_name": "Landes",
        "email": "alandesb@toplist.cz",
        "gender": "Bigender",
        "ip_address": "158.94.171.175"
    },
    {
        "id": 13,
        "first_name": "Sigrid",
        "last_name": "Bacchus",
        "email": "sbacchusc@dyndns.org",
        "gender": "Non-binary",
        "ip_address": "68.112.180.196"
    },
    {
        "id": 14,
        "first_name": "Fernande",
        "last_name": "Gritskov",
        "email": "fgritskovd@instagram.com",
        "gender": "Female",
        "ip_address": "12.173.166.123"
    },
    {
        "id": 15,
        "first_name": "Con",
        "last_name": "Hutchison",
        "email": "chutchisone@blogger.com",
        "gender": "Genderfluid",
        "ip_address": "252.13.134.39"
    }
]

console.log(data);

// filter : 해당조건을 만족하는 것만 가져오는 것> map : 아이디,fn,lln,email만 가져올 것) >  forEach 메소드로 
// 여성회원 리스트.
// 표 형식으로다가..



const filterGender = data.filter(function (val) {
    return val.gender === 'Female';
});
console.log(filterGender);

const  newData = filterGender.map(function(val){
    let newObj = {}
    newObj.uid = val.id;
    newObj.fname = val.first_name;
    newObj.lname = val.last_name;
    newObj.umail = val.email;
    return newObj;

})
console.log(newData);
let str = showList;
function showList(){

    let str = '<h1>여성회원리스트</h1>'
    str += '<table border = "1" >';
    str += '<thead ><th style ="backgroundColor = lightyellow">' + 'id' + '</th>' + '<th>' + 'First_name' + '</th>' + '<th>'+ 'Last_name' + '<th>' + 'email' + '</th></thead>';
    str +='<tbody>';
    filterGender.forEach(callBackFnc);
    str+= '</tbody>';
    str+='</table>';
    document.write(str);
}

function callBackFnc(newObj){
    str += '<tr><td>'+ newObj.id + '</td>'+'<td>' + newObj.first_name  + '</td>' +'<td>' + newObj.last_name +'</td>' + '<td>'+ newObj.email +'</td></tr>';
}