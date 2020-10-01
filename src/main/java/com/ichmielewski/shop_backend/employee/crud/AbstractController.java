package com.ichmielewski.shop_backend.employee.crud;

import com.ichmielewski.shop_backend.dto.AbstractDTO;
import com.ichmielewski.shop_backend.pdf.PdfBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


public abstract class AbstractController <DTO extends AbstractDTO> {

    private AbstractClient<DTO> client;
    private final String pdfName;
    private final AbstractService<DTO> service;

    public AbstractController(AbstractClient<DTO> client, String pdfName, AbstractService<DTO> service) {
        this.client = client;
        this.pdfName = pdfName;
        this.service = service;
    }

    @GetMapping
    List<DTO> getAll(){
        return client.getAll();
    }

    @GetMapping("/{id}")
    DTO getById(@PathVariable(name = "id") Long id){
        return client.getById(id);
    }

    @PostMapping
    DTO add(@Valid @RequestBody DTO dto){
        return client.add(dto);
    }

    @PutMapping("/{id}")
    DTO update(@PathVariable(name = "id") Long id, @Valid @RequestBody DTO dto){
        return client.update(id,dto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable(name = "id") Long id){
        client.delete(id);
    }

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getPdf() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+pdfName+".pdf\"")
                .body(PdfBuilder.build(client.getAll()));
    }
    @PostMapping("/send")
    public void sendMail(@RequestBody String mail) {
        service.sendMail(mail);
    }


}
