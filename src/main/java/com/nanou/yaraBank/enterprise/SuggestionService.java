
package com.nanou.yaraBank.enterprise;

import com.nanou.yaraBank.exception.Response.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public interface SuggestionService
{
    Response addSuggestion(SuggestionDomain suggestion);
    Response suggestions();
}
