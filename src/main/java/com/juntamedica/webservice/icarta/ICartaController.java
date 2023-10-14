package com.juntamedica.webservice.icarta;

import com.juntamedica.webservice.icarta.service.ICartaService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("webservice/icarta")
public class ICartaController {

    @Autowired
    private ICartaService iCartaService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("emailconfirmation")
    public void emailConfirmation(@RequestHeader("X-iCarta-Message-ID") String messageId,
                                  @RequestHeader("X-iCarta-Event-Type") String eventType,
                                  @RequestBody JSONObject bodyConfirmation) {
        iCartaService.receiveICartaConfirmation(messageId, eventType, bodyConfirmation);
    }
}
