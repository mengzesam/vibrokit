/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mzs.vibrokit.recyclerview;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

import com.mzs.vibrokit.BR;
import com.mzs.vibrokit.databinding.OnediskItemBinding;
import com.mzs.vibrokit.model.OnediskViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An Adapter implementation that works with a {@link DataBoundViewHolder}.
 * <p>
 * Although this version enforces a single item type, it can easily be extended to support multiple
 * view types.
 *
 * @param <T> The type of the binding class
 */
abstract public class DataBoundAdapter<T extends ViewDataBinding> extends
        BaseDataBoundAdapter<T> {
    @LayoutRes
    private final int mLayoutId;
    private List<Object> mItemList=new ArrayList<>();

    /**
     * Creates a DataBoundAdapter with the given item layout
     *
     * @param layoutId The layout to be used for items. It must use data binding.
     */
    public DataBoundAdapter(@LayoutRes int layoutId,Object... items) {
        mLayoutId = layoutId;
        Collections.addAll(mItemList, items);
    }

    @Override
    public int getItemLayoutId(int position) {
        return mLayoutId;
    }


    @Override
    protected void bindItem(DataBoundViewHolder<T> holder, int position, List<Object> payloads) {
        holder.binding.setVariable(BR.data,mItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public Object getItem(int position) {
        return mItemList.get(position);
    }

    public void addItem(Object item) {
        mItemList.add(item);
        notifyItemInserted(mItemList.size() - 1);
    }

    public void addItem(int position, Object item) {
        mItemList.add(position, item);
        notifyItemInserted(position);
    }

    //    public void removeItems(int fromPos,int toPos){
   public void removeItems(){
        int n=mItemList.size();
        if(n>1) {
            mItemList.removeAll(mItemList.subList(1,n));
            notifyItemRangeRemoved(1,n-1);
        }
    }
}
