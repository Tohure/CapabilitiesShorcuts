package io.tohure.capabilitiesdemo.view.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.tohure.capabilitiesdemo.R
import io.tohure.capabilitiesdemo.databinding.ProductCardRowBinding
import io.tohure.capabilitiesdemo.model.Product

class ProductAdapter : ListAdapter<Product, ProductAdapter.ViewHolder>(ProductDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductCardRowBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)
        with(holder.binding) {
            tvProductTitle.text = product.title
            tvProductPrice.text = "$${product.price}"
            Glide
                .with(ivProductImage.context)
                .load(product.image)
                .centerCrop()
                .placeholder(R.drawable.ic_load_product)
                .into(ivProductImage)
        }
    }

    inner class ViewHolder(val binding: ProductCardRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    class ProductDiffCallBack : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem
    }
}