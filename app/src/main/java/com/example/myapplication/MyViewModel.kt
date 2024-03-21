import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MyViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    // Clé pour stocker et récupérer l'heure
    private val HEURE_KEY = "heure_key"

    // Clé pour stocker et récupérer le nom
    private val NOM_KEY = "nom_key"

    fun setHeure(heure: String){
        savedStateHandle[HEURE_KEY] = heure
    }

    fun setNom(nom: String) {
        savedStateHandle[NOM_KEY] = nom
    }
}