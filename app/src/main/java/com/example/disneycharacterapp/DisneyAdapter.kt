import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.disneycharacterapp.R

class DisneyAdapter(private val disneyList: List<String>) : RecyclerView.Adapter<DisneyAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val disneyImage: ImageView
        val disneyName : TextView
        val disneyFilms : TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            disneyImage = view.findViewById(R.id.disney_image)
            disneyName = view.findViewById(R.id.name)
            disneyFilms = view.findViewById(R.id.films)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisneyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.disney_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisneyAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(disneyList[position])
            .centerCrop()
            .into(holder.disneyImage)

        holder.disneyImage.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Disney character at position $position clicked", Toast.LENGTH_SHORT).show()
        }

//        holder.disneyName.text = "Character Name: ${name}"
//        holder.disneyFilms.text = "Films: ${films}"
    }

    override fun getItemCount() = disneyList.size
}
