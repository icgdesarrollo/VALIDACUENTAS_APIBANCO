package ICG.FormCreator;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import ICG.FormCreator.DATA.AccountRepository;
import ICG.FormCreator.DATA.Account;

@RestController
@CrossOrigin(origins = "*")

public class AccountsController {
    Logger logger = LoggerFactory.getLogger(AccountsController.class);
    @Autowired AccountRepository repo;

    @RequestMapping(value = "/liveprobeness/", method = RequestMethod.GET)
     public String getQuery() {
        logger.debug("live probeness");
        return "alive!";
      }

      @RequestMapping(value = "/validations/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  
  
  
      public Object postComments( @RequestBody String input) {
            try {
          
                logger.info("incoming account validation "+input);
                JSONObject res=new JSONObject();
                String inputCap=input.toUpperCase();
                JSONObject jsoninput=new JSONObject(inputCap);
                String account=jsoninput.getJSONObject("PAYLOAD").getString("ACCOUNT");
                int currency=jsoninput.getJSONObject("PAYLOAD").getInt( "CURRENCY");
                int product=jsoninput.getJSONObject("PAYLOAD").getInt("PRODUCT");
                //search for record
                Iterable<Account> cuenta= repo.findByAccountAndProductAndCurrency(account,product,currency);
                String name="";
                int status=0;
              int found=0;
                for(Account a: cuenta){
                  logger.info("record found "+a.getName());
                  name=a.getName();
                  if(a.getStatus()==0){
                    found=1;
                  }
                  else{
                    status=a.getStatus();
                  }
                }

                
                res.put("Destination",jsoninput.getString("SOURCE"));
                res.put("Source",jsoninput.getString("DESTINATION"));
                res.put("UUID",jsoninput.getString("UUID"));
                res.put("type","002");
                res.put("payload",new JSONObject());

                // posibles estados:
                /*
                 * ○ ERR – (Incorrecta)
                  ○ CAN – (Cancelada)
                  ○ INC – (Inactiva)
                  ○ BLOC – (Bloqueada)
                  ○ EMB – (Embargada)
                 */

                if(found==0){
                  res.getJSONObject("payload").put("found","false");
                  res.getJSONObject("payload").put("name","");
                  if(status==6){
                    res.getJSONObject("payload").put("status","INC");
                  }else if(status==3){
                    res.getJSONObject("payload").put("status","BLOC");
                  }else if(status==2){
                     res.getJSONObject("payload").put("status","BLOC");  
                  }
                  else{
                     res.getJSONObject("payload").put("status","ERR");  
                  }
                  
                }else{
                  res.getJSONObject("payload").put("found","true");
                  res.getJSONObject("payload").put("name",name);
                  res.getJSONObject("payload").put("status","");
                }
                
                return res.toString();
              }catch(Exception ex){
                logger.info(ex.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
              }
            }
   
      
}
