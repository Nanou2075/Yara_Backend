
package com.nanou.yaraBank.enterprise;

import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface SuggestionResource {
    @PostMapping("suggestion")
    Response addSuggestion( @RequestBody SuggestionDomain suggestionDomain);
    @GetMapping("suggestions")
    Response suggestion();
}
