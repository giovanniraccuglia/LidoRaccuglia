<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it">
   <head>
   </head>
   <body>
      <!-- Form Pagamento -->
      <!-- The Modal -->
      <div class="modal fade" id="modalPagamento">
         <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
               <!-- Modal header -->
               <div class="modal-header">
                  <div class="row">
                     <div class="col-md-7 w3-opacity">
                        <h3>Carta Debito/Credito</h3>
                     </div>
                     <div class="col-md-5 text-right"> <img src="https://img.icons8.com/color/36/000000/visa.png"> <img src="https://img.icons8.com/color/36/000000/mastercard.png"> <img src="https://img.icons8.com/color/36/000000/amex.png"> </div>
                  </div>
               </div>
               <!-- Modal body -->
               <div class="modal-body">
                  <div id="divAlertPagamento"></div>
                  <form id="formPagamento" method="post">
                     <div class="form-group">
                        <label for="cc-number" class="w3-opacity">Numero Carta:</label> 
                        <input type="text" class="form-control" id="cc-number" placeholder="Inserire Numero Carta" name="cc-number" title="Inserire solamente caratteri numerici" pattern="[0-9]{16}" maxlength="16" required>
                     </div>
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <label for="cc-exp" class="w3-opacity">Scadenza Carta:</label> 
                              <input type="text" class="form-control" id="cc-exp" placeholder="MM / YY" name="cc-exp" title="Inserire solamente caratteri numerici separati da '/'" pattern="(?:0[1-9]|1[0-2])/[0-9]{2}" maxlength="5" required>
                           </div>
                        </div>
                        <div class="col-md-6">
                           <div class="form-group">
                              <label for="cc-cvc" class="w3-opacity">CVC:</label> 
                              <input type="text" class="form-control" id="cc-cvc" placeholder="CVC" name="cc-cvc" title="Inserire solamente caratteri numerici" pattern="[0-9]{3,4}" maxlength="4" required>
                           </div>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="numeric" class="w3-opacity">Intestatario Carta:</label>
                        <input type="text" class="form-control" id="holderName" placeholder="Inserire Intestatario Carta" name="holderName" title="Inserire solamente caratteri alfabetici" pattern="[a-zA-Zмийтащ ]+" maxlength="45" required>
                     </div>
                     <div class="modal-footer" style="font-style: normal">
                        <button id="closePagamento" type="button" class="btn btn-secondary" data-dismiss="modal"><i class="fa fa-close"></i> close</button>
                        <button type="submit" class="btn bg-info active" value="pagamento" style="color: white"><i class="fa fa-credit-card"></i> paga</button>
                     </div>
                  </form>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>