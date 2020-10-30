<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <script src="js/generale/menu.js"></script>
   </head>
   <body>
      <!-- Menu -->
      <!-- Modal -->
      <div class="modal fade" id="modalMenu">
         <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
               <!-- Modal Header -->
               <div class="modal-header">
                  <h2 class="modal-title w3-opacity">Menù</h2>
                  <!-- Button for category -->
                  <div class="btn-group" style="font-style: normal; align-self: center">
                     <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Bar</button>
                        <div class="dropdown-menu">
                           <a id="linkColazione" class="dropdown-item" href="#">Colazione</a>
                           <a id="linkPanini" class="dropdown-item" href="#">Panini</a>
                           <a id="linkRustici" class="dropdown-item" href="#">Rustici</a>
                           <a id="linkInsalatone" class="dropdown-item" href="#">Insalatone</a>
                           <a id="linkFruttaDessert" class="dropdown-item" href="#modalMenu">Frutta e Dessert</a>
                        </div>
                     </div>
                     <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Ristorante</button>
                        <div class="dropdown-menu">
                           <a id="linkAntipastiMare" class="dropdown-item" href="#">Antipasti di Mare</a>
                           <a id="linkAntipastiTerra" class="dropdown-item" href="#">Antipasti di Terra</a>
                           <a id="linkPrimiMare" class="dropdown-item" href="#">Primi di Mare</a>
                           <a id="linkPrimiTerra" class="dropdown-item" href="#">Primi di Terra</a>
                           <a id="linkSecondiMare" class="dropdown-item" href="#">Secondi di Mare</a>
                           <a id="linkSecondiTerra" class="dropdown-item" href="#">Secondi di Terra</a>
                           <a id="linkDessert" class="dropdown-item" href="#">Dessert</a>
                        </div>
                     </div>
                     <div class="btn-group">
                        <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown">Bevande</button>
                        <div class="dropdown-menu">
                           <a id="linkCaffetteria" class="dropdown-item" href="#">Caffetteria</a>
                           <a id="linkBirre" class="dropdown-item" href="#">Birre</a>
                           <a id="linkCocktails" class="dropdown-item" href="#">Cocktails</a>
                           <a id="linkViniBianchi" class="dropdown-item" href="#">Vini Bianchi</a>
                           <a id="linkViniRossi" class="dropdown-item" href="#">Vini Rossi</a>
                           <a id="linkDistillati" class="dropdown-item" href="#">Distillati</a>
                           <a id="linkAnalcolici" class="dropdown-item" href="#">Analcolici</a>
                        </div>
                     </div>
                  </div>
               </div>
               <!-- Modal body -->
               <div id="divMenu" class="modal-body">
                  <!-- Table Menù -->
               </div>
               <!-- Modal footer -->
               <div class="modal-footer" style="font-style: normal">
                  <button id="closeMenu" type="button" class="btn btn-secondary" data-dismiss="modal" ><i class="fa fa-close"></i> close</button>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>