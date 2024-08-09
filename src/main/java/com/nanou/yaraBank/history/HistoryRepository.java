
package com.nanou.yaraBank.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository  extends JpaRepository<HistoryDomain,String> {
}
