
package com.nanou.yaraBank.enterprise;

import com.nanou.yaraBank.account.AccountDomain;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.exception.Response.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.nanou.yaraBank.exception.Response.Message.OK;
import static com.nanou.yaraBank.exception.Response.Message.VOTRE_SUGGESTION_A_ETE_PRIS_EN_COMPTE;
import static com.nanou.yaraBank.exception.Response.Security.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService
{
    private final SuggestionRepository suggestionRepository;

    @Override
    public Response addSuggestion(SuggestionDomain suggestion) {
        suggestionRepository.save(suggestion);
        return new Response(OK,VOTRE_SUGGESTION_A_ETE_PRIS_EN_COMPTE);
    }

    @Override
    public Response suggestions() {
        List<SuggestionDomain> suggestions = suggestionRepository.findAll();
        if (suggestions.isEmpty()){
            throw new EntityNotFoundException(NO,EMPTY_MESSAGE);
        }
        return new Response(OK,suggestions);
    }
}
