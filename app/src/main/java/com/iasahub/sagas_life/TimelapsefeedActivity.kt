package com.iasahub.sagas_life

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iasahub.sagas_life.databinding.ActivityTimelapsefeedBinding
import kotlinx.android.synthetic.main.activity_timelapsefeed.*

class TimelapsefeedActivity : AppCompatActivity(), OnTimelapseItemClickListener {

    lateinit var binding: ActivityTimelapsefeedBinding
    lateinit var timelapse_feed_list: ArrayList<Timelapses>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timelapsefeed)
        timelapse_feed_list = ArrayList()

        addTimelapse()
        TimeRecycler.layoutManager = LinearLayoutManager(this)
        TimeRecycler.addItemDecoration((DividerItemDecoration(this, 1)))
        TimeRecycler.adapter = TimelapseAdapter(timelapse_feed_list, this)


        setSupportActionBar(toolbar)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                Toast.makeText(this, query, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)


        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))

        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.map -> {
            startActivity(Intent(this, MapsActivity::class.java))
            true
        }
        R.id.search -> {
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    fun addTimelapse() {
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in the Universe",
                "#universe #sky #nice",
                R.drawable.user_icon,
                R.drawable.timelapse_1
            )
        )
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in the Universe",
                "#universe #sky #nice",
                R.drawable.user_icon,
                R.drawable.timelapse_2
            )
        )
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in the Universe",
                "#universe #sky #nice",
                R.drawable.user_icon,
                R.drawable.timelapse_3
            )
        )
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in Alps",
                "#apls #lake #landscape",
                R.drawable.user_icon,
                R.drawable.timelapse_4
            )
        )
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in Alps",
                "#apls #lake #landscape",
                R.drawable.user_icon,
                R.drawable.timelapse_5
            )
        )
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in Alps",
                "#apls #lake #landscape",
                R.drawable.user_icon,
                R.drawable.timelapse_6
            )
        )
        timelapse_feed_list.add(
            Timelapses(
                "Somewhere in Alps",
                "#apls #lake #landscape",
                R.drawable.user_icon,
                R.drawable.timelapse_7
            )
        )
    }

    override fun onItemClick(item: Timelapses, position: Int) {
        val intent = Intent(this, TimelapsePageActivity::class.java)
        intent.putExtra("TNAME", item.name)
        intent.putExtra("TDESCR", item.description)
        intent.putExtra("TUSERPIC", item.logo.toString())
        intent.putExtra("TIMAGE", item.timelapsePic.toString())
        startActivity(intent)
    }
}































