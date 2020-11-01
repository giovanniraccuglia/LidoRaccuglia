<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
   <head>
   </head>
   <body>
      <!-- Menu -->
      <!-- Modal -->
      <div class="modal fade" id="modalNuovoOrdine">
         <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
               <!-- Modal Header -->
               <div class="modal-header">
                  <h2 class="modal-title w3-opacity">Ordina</h2>
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
                           <a id="linkAntipastiMare" class="dropdown-item" href="#">Antipasti Di Mare</a>
                           <a id="linkAntipastiTerra" class="dropdown-item" href="#">Antipasti Di Terra</a>
                           <a id="linkPrimiMare" class="dropdown-item" href="#">Primi Di Mare</a>
                           <a id="linkPrimiTerra" class="dropdown-item" href="#">Primi Di Terra</a>
                           <a id="linkSecondiMare" class="dropdown-item" href="#">Secondi Di Mare</a>
                           <a id="linkSecondiTerra" class="dropdown-item" href="#">Secondi Di Terra</a>
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
               <div id="divAlertNuovoOrdine" style="font-style: normal; padding: 1%"></div>
               <!-- Modal body -->
               <div id="divNuovoOrdine" class="modal-body">
                  <!-- Table Menù -->
               </div>
               <!-- Modal footer -->
               <div class="modal-footer" style="font-style: normal">
               	  <div id="totaleNuovoOrdine" class="w3-opacity" style="flex: auto; padding-left: 2%"></div>
                  <button id="closeNuovoOrdine" type="button" class="btn btn-secondary" data-dismiss="modal" ><i class="fa fa-close"></i> close</button>
                  <button id="nuovoOrdine" class="btn bg-info active" value="nuovoOrdine" style="font-style: normal; color: white"><i class="fa fa-shopping-cart"></i> ordina</button>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>