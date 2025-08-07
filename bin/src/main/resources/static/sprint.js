function calcular(){
    var fechaini = getElementById('fechaInicio').value;
    var fechafin = getElementById('fechaFinal').value;
    var diasdif= fechafin.getTime()-fechaini.getTime();
    var contdias = Math.round(diasdif/(1000*60*60*24));
    alert(contdias);
}