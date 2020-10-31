<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
   <head>
      <style>
         .grid-container {
         overflow-x: scroll;
         display: grid;
         grid-gap: 1px;
         background-color: #F2D16B;
         }
         .grid-container > div {
         text-align: center;
         }
         .postazione {
         margin: 2px;
         }
         .right{
         float: right;
         width: 25%;
         text-align: left;
         }
         .left{
         float: left;
         width: 75%;
         text-align: left;
         }
      </style>
   </head>
   <body>
      <!-- Vista Spiaggia -->
      <!-- The Modal -->
      <div class="modal fade" id="modalNuovaPrenotazione">
         <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
               <!-- Modal Header -->
               <div class="modal-header">
                  <h2 class="modal-title w3-opacity">Prenota</h2>
               </div>
               <!-- Modal body -->
               <div id="divNuovaPrenotazione" class="modal-body">
                  <!-- Date picker -->
                  <div id="divAlertNuovaPrenotazione" style="font-style: normal"></div>
                  <div class="form-group row" style="font-style: normal">
                     <label for="date" class="col-2 col-form-label" style="font-size: 1.4rem">Data</label>
                     <div class="col-5">
                        <input class="form-control" type="date" id="date" name="date">
                     </div>
                  </div>
                  <!-- Legenda -->
                  <div style="display: flex">
                     <div class="left">
                        <h4>Legenda</h4>
                        <i class="fa fa-square" aria-hidden="true" style="color: #F2D16B; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;"></i><small class="w3-opacity"> Libero</small><br>
                        <i class="fa fa-square" aria-hidden="true" style="color: lightcoral; text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;"></i><small class="w3-opacity"> Occupato</small><br>
                        <br><small class="w3-opacity">NB. Ogni singola postazione è comprensiva di 2 sdraio e 1 ombrellone.</small>
                     </div>
                     <div class="right">
                        <h4>Prezzi</h4>
                        <small class="w3-opacity">Postazioni in 1&deg; fila: 17&nbsp;&euro;</small><br>
                        <small class="w3-opacity">Postazioni in 2&deg; fila: 15&nbsp;&euro;</small><br>
                        <small class="w3-opacity">Postazioni in 3&deg; fila: 12&nbsp;&euro;</small><br>
                        <small class="w3-opacity">Postazioni in 4&deg; e 5&deg; fila: 10&nbsp;&euro;</small>
                     </div>
                  </div>
                  <br><br>
                  <div id="mappaLido" class="grid-container">
                     <!-- Grid Spiaggia -->
                  </div>
               </div>
               <!-- Modal footer -->
               <div class="modal-footer" style="font-style: normal">
                  <button id="closeNuovaPrenotazione" type="button" class="btn btn-secondary" data-dismiss="modal" ><i class="fa fa-close"></i> close</button>
                  <button id="nuovaPrenotazione" class="btn bg-info active" value="nuovaPrenotazione" style="font-style: normal; color: white"><i class="fa fa-check"></i> prenota</button>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>