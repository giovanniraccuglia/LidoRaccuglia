<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ page import="com.raccuglia.model.Utente"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <title>Marrakech Beach - Home Page</title>
      <link rel="shortcut icon" href="img/iconaLido.ico">
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
      <script src="js/generale/index.js"></script>
      <style>
         .jumbotron {
         background-color: #5e7a8c;
         }
         h1, h2, h3, h4, h5, h6, p, label {
         font-family: -webkit-pictograph;
         }
         h2 {
         font-size: 2.3rem;
         }
         p {
         font-size: 1.3rem;
         }
         label {
         font-style: italic;
         font-size: 1.8rem;
         }
         nav {
         font-size: 1.2rem;
         }
         body, .modal-content {
         background-color: whitesmoke;
         }
      </style>
   </head>
   <body>
      <!-- header e navbar -->
      <%@ include file="/WEB-INF/generale/header.jsp"%>
      <!-- body -->
      <div class="container" style="margin-top:30px; font-style: italic">
         <div class="row">
            <!-- info -->
            <div class="col-sm-4" style="padding-right: 3%">
               <!-- link menù - solarium -->
               <h3 class="w3-opacity">Listino Prezzi</h3>
               <ul style="padding-right: 25%" class="nav nav-pills flex-column">
                  <li class="nav-item">
                     <!-- Modal Menù -->
                     <a id="linkMenu" class="nav-link bg-info active" href="" data-toggle="modal" data-target="#modalMenu" style="font-style: normal"><i class="fa fa-cutlery"></i>&nbsp;&nbsp;Menù</a>
                     <!-- The Modal -->
                     <%@ include file="/WEB-INF/generale/menu.jsp"%>
                  </li>
                  <li>&nbsp;</li>
                  <li class="nav-item">
                     <!-- Modal Spiaggia -->
                     <a id="linkSpiaggia" class="nav-link bg-info active" href="" data-toggle="modal" data-target="#modalSpiaggia" style="font-style: normal"><i class="fa fa-sun-o"></i>&nbsp;&nbsp;Spiaggia</a>
                     <!-- Modal -->
                     <%@ include file="/WEB-INF/generale/spiaggia.jsp"%>
                  </li>
               </ul>
               <br><br>
               <!-- posizione -->
               <h3 class="w3-opacity">Dove Siamo</h3>
               <p class="w3-opacity" style="display:inline">Via Lungomare Cristoforo Colombo</p>
               <p class="w3-opacity">90044, Carini, Sicilia, Italy</p>
               <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d12545.56061077882!2d13.1611655!3d38.1776105!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb83acf9334003523!2sMarrakech%20Beach!5e0!3m2!1sit!2sit!4v1602593850333!5m2!1sit!2sit" width="100%" height="50%" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
               <br><br><br>
               <!-- contatti -->
               <h3 class="w3-opacity">Contattaci</h3>
               <p class="w3-opacity" style="display:inline">Tel. +39 0123456789</p>
               <p class="w3-opacity">E-mail: lido1mare@gmail.com</p>
               <hr class="d-sm-none">
            </div>
            <!-- description -->
            <div class="col-sm-8">
               <!-- first section -->
               <h2 class="w3-opacity">Lido Marrakech Beach</h2>
               <div id="demo" class="carousel slide" data-ride="carousel">
                  <ul class="carousel-indicators">
                     <li data-target="#demo" data-slide-to="0" class="active"></li>
                     <li data-target="#demo" data-slide-to="1"></li>
                  </ul>
                  <div class="carousel-inner">
                     <div class="carousel-item active">
                        <img src="https://img.grouponcdn.com/deal/gqjG1ivbMT3fGjLs97w2/Lv-2048x1228/v1/c700x420.webp" alt="Sdraio" width="700" height="350">
                     </div>
                     <div class="carousel-item">
                        <img src="https://img.grouponcdn.com/deal/TME5nFxkGV3881fEiM6BRGpqUsf/TM-2048x1229/v1/c700x420.webp" alt="Spiaggia" width="700" height="350">
                     </div>
                  </div>
                  <a class="carousel-control-prev" href="#demo" data-slide="prev">
                  <span class="carousel-control-prev-icon"></span>
                  </a>
                  <a class="carousel-control-next" href="#demo" data-slide="next">
                  <span class="carousel-control-next-icon"></span>
                  </a>
               </div>
               <br>
               <p class="w3-opacity">Ascoltate il mare e godetevi il sole... Trascorrerete una giornata in una spiaggia esclusiva, godendo di tutti i comfort.</p>
               <br>
               <!-- second section -->
               <h2 class="w3-opacity">Bar &amp; Ristorante</h2>
               <div id="demo1" class="carousel slide" data-ride="carousel">
                  <ul class="carousel-indicators">
                     <li data-target="#demo1" data-slide-to="0" class="active"></li>
                     <li data-target="#demo1" data-slide-to="1"></li>
                  </ul>
                  <div class="carousel-inner">
                     <div class="carousel-item active">
                        <img src="https://www.mediterraneosorrento.com/wp-content/uploads/2019/09/Mediterraneo-39slide.jpg" alt="Tramonto" width="700" height="350">
                     </div>
                     <div class="carousel-item">
                        <img src="https://www.ilmeridianonews.it/wp-content/uploads/2019/07/aperitivo-in-spiaggia-660x330.jpg" alt="Drink" width="700" height="350">
                     </div>
                  </div>
                  <a class="carousel-control-prev" href="#demo1" data-slide="prev">
                  <span class="carousel-control-prev-icon"></span>
                  </a>
                  <a class="carousel-control-next" href="#demo1" data-slide="next">
                  <span class="carousel-control-next-icon"></span>
                  </a>
               </div>
               <br>
               <p class="w3-opacity">Colazione, snack, light lunch, e cocktail da godere con vista mare.</p>
            </div>
         </div>
      </div>
      <!-- footer -->
      <%@ include file="/WEB-INF/generale/footer.jsp"%>
   </body>
</html>