const API = window.location.origin;

const jwtToken = localStorage.getItem("auth")

function navigate(path) {
window.location.href = `${window.location.origin}${path}`
}

if(!jwtToken) {
    if(window.location.pathname === '/') {
	    navigate("/login")
	}
}

var multiplicador = 7;


function laranja()  {
return 1 + (360 * multiplicador)
}

function vermelho()  {
return 100 + (360 * multiplicador)
}

function amarelo()  {
return 200 + (360 * multiplicador)
}

function roxo()  {
return 30 + (360 * multiplicador)
}



const fns = {
    orange: laranja,
    red: vermelho,
    yellow: amarelo,
    purple: roxo
}

function rodar(cor, color) {
$('body').css('background-color', 'var(--bg-cont-b');
  $('.ruleta').css('pointer-events', 'none');
  $('.colores').removeClass('animacion');
   var color = fns[cor]();

  $('#animacion').empty().append(
      '@keyframes animacion{'+
        '0%{transform: rotate(0deg)}'+
        '100%{transform: rotate('+color+'deg)}'+
      '}'
  );



  setTimeout(function(){
    $('.colores').addClass('animacion')
    //$('.colores').css('animation', 'animacion 4s ease-out forwards');
  },1)

   setTimeout(function(){
   if(color === cor) {
    alert('Yeeah you win!')
   }else {
   alert(':( You loses')
   }
     updateBalance()
   },4000);

}

$('.ruleta').click(function(){

 })


function colorAleatorio(min, max) {
  var colorAleatorio = ((Math.random() * (max - min) + min).toFixed(0))*1;
  colorAleatorio = colorAleatorio + (360 * multiplicador);
  return colorAleatorio;
}