

package com.nanou.yaraBank.enterprise;

import com.nanou.yaraBank.exception.Response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SuggestionController implements SuggestionResource
{
    private final SuggestionService suggestionService;

    @Override
    public Response addSuggestion( SuggestionDomain suggestionDomain) {
        return suggestionService.addSuggestion(suggestionDomain);
    }

    @Override
    public Response suggestion() {
        return suggestionService.suggestions();
    }
}


