package profiles

import com.datastax.oss.driver.api.core.CqlSession

trait DBSession {

  def session : Unit

}
