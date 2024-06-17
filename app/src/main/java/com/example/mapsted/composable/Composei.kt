import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mapsted.R
import com.example.mapsted.model.BuildingModel
import com.example.mapsted.model.DeviceModel
import com.example.mapsted.model.PurchaseModel
import com.example.mapsted.viewmodel.BuildingViewModel

@Composable
fun BuildCompose(buildingViewModel: BuildingViewModel) {
    val buildings by buildingViewModel.buildings.observeAsState(emptyList())
    val devices by buildingViewModel.device.observeAsState(emptyList())
    if (buildings.isNotEmpty() && devices.isNotEmpty())
        MyLayout(buildings, devices)
}

@Composable
fun MyLayout(buildings: List<BuildingModel>, devices: List<DeviceModel>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        var expandedManufactureList by remember { mutableStateOf(false) }
        var expandedCategoryList by remember { mutableStateOf(false) }
        var expandedCountryList by remember { mutableStateOf(false) }
        var expandedStateList by remember { mutableStateOf(false) }
        var expandedItemList by remember { mutableStateOf(false) }

        val manufactureList = devices as ArrayList<DeviceModel>
        val categoryList =
            devices[0].mUsageStatistics.mSessionInfo[0].mPurchases as ArrayList<PurchaseModel>
        val itemList =
            devices[0].mUsageStatistics.mSessionInfo[0].mPurchases as ArrayList<PurchaseModel>
        val buildingList = buildings as ArrayList<BuildingModel>

        var selectedManufacture by remember { mutableStateOf(manufactureList[0]) }
        var selectedCategory by remember { mutableStateOf(categoryList[0]) }
        var selectedItem by remember { mutableStateOf(itemList[0]) }
        var selectedCountry by remember { mutableStateOf(buildings[0]) }
        var selectedState by remember { mutableStateOf(buildings[0]) }
        Text(
            text = stringResource(R.string.agilarasan_16jun2024),
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp), style = TextStyle(lineHeight = 21.sp)
        )
        Text(
            text = stringResource(R.string.manufacture),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 15.dp)
                .background(colorResource(R.color.titlebg))
                .wrapContentSize(Alignment.CenterStart), color = colorResource(R.color.titletxt)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(color = colorResource(R.color.rowitembg))
        ) {
            Text(
                text = stringResource(R.string.manufacture),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically), color = colorResource(R.color.rowitemtxt)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .wrapContentHeight()
                    .background(colorResource(R.color.spinnerbg))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = selectedManufacture.mManufacturer,
                    modifier = Modifier
                        .background(colorResource(R.color.spinnerbg))
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { expandedManufactureList = true },
                    color = colorResource(R.color.rowitemtxt)
                )
                DropdownMenu(
                    expanded = expandedManufactureList,
                    onDismissRequest = { expandedManufactureList = false }
                ) {
                    manufactureList.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.mManufacturer) },
                            onClick = {
                                selectedManufacture = option
                                expandedManufactureList = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = selectedManufacture.mUsageStatistics.getTotalCost(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 5.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically), color = colorResource(R.color.rowitemtxt)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(color = colorResource(R.color.rowitembg))
        ) {
            Text(
                text = stringResource(R.string.category),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically), color = colorResource(R.color.rowitemtxt)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .wrapContentHeight()
                    .background(colorResource(R.color.spinnerbg))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = selectedCategory.getItemCategoryIdString(),
                    modifier = Modifier
                        .background(colorResource(R.color.spinnerbg))
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { expandedCategoryList = true },
                    color = colorResource(R.color.rowitemtxt)
                )
                DropdownMenu(
                    expanded = expandedCategoryList,
                    onDismissRequest = { expandedCategoryList = false }
                ) {
                    categoryList.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.getItemCategoryIdString()) },
                            onClick = {
                                selectedCategory = option
                                expandedCategoryList = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = selectedCategory.getCost(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 5.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically), color = colorResource(R.color.rowitemtxt)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(color = colorResource(R.color.rowitembg))
        ) {
            Text(
                text = stringResource(R.string.country),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically), color = colorResource(R.color.rowitemtxt)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .wrapContentHeight()
                    .background(colorResource(R.color.spinnerbg))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = selectedCountry.country ?: "",
                    modifier = Modifier
                        .background(colorResource(R.color.spinnerbg))
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { expandedCountryList = true },
                    color = colorResource(R.color.rowitemtxt)
                )
                DropdownMenu(
                    expanded = expandedCountryList,
                    onDismissRequest = { expandedCountryList = false }
                ) {
                    buildings.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.country ?: "") },
                            onClick = {
                                selectedCountry = option
                                expandedCountryList = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = selectedItem.getCost(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 5.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically),
                color = colorResource(R.color.rowitemtxt)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(color = colorResource(R.color.rowitembg))
        ) {
            Text(
                text = stringResource(R.string.state),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically),
                color = colorResource(R.color.rowitemtxt)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .wrapContentHeight()
                    .background(colorResource(R.color.spinnerbg))
                    .padding(horizontal = 15.dp)
            ) {
                Text(
                    text = selectedState.state ?: "",
                    modifier = Modifier
                        .background(colorResource(R.color.spinnerbg))
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { expandedStateList = true },
                    color = colorResource(R.color.rowitemtxt)
                )
                DropdownMenu(
                    expanded = expandedStateList,
                    onDismissRequest = { expandedStateList = false }
                ) {
                    buildings.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option.state ?: "") },
                            onClick = {
                                selectedState = option
                                expandedStateList = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = selectedItem.getCost(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 5.dp)
                    .wrapContentHeight()
                    .align(Alignment.CenterVertically),
                color = colorResource(R.color.rowitemtxt)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.number_of_purchases),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(top = 15.dp, start = 15.dp)
                    .background(colorResource(R.color.titlebg))
                    .wrapContentSize(Alignment.CenterStart), color = colorResource(R.color.titletxt)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(color = colorResource(R.color.rowitembg))
            ) {
                Text(
                    text = stringResource(R.string.item),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically),
                    color = colorResource(R.color.rowitemtxt)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                        .wrapContentHeight()
                        .background(colorResource(R.color.spinnerbg))
                        .padding(horizontal = 15.dp)
                ) {
                    Text(
                        text = selectedItem.getItemCategoryIdString(),
                        modifier = Modifier
                            .background(colorResource(R.color.spinnerbg))
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable { expandedItemList = true },
                        color = colorResource(R.color.rowitemtxt)
                    )
                    DropdownMenu(expanded = expandedItemList,
                        onDismissRequest = { expandedItemList = false }
                    ) {
                        itemList.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(text = option.itemID) },
                                onClick = {
                                    selectedItem = option
                                    expandedItemList = false
                                }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = selectedItem.itemID,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, end = 5.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically),
                    color = colorResource(R.color.rowitemtxt)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                text = stringResource(R.string.most_total_purchases),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(top = 15.dp, start = 15.dp)
                    .background(colorResource(R.color.titlebg))
                    .wrapContentSize(Alignment.CenterStart), color = colorResource(R.color.titletxt)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(color = colorResource(R.color.rowitembg))
            ) {
                Text(
                    text = stringResource(R.string.building),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically), color = colorResource(R.color.titletxt)
                )
                Text(
                    text = buildingList.find { it.buildingId == selectedManufacture.getBuildId() }?.buildingName
                        ?: "",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterVertically), color = colorResource(R.color.titletxt)
                )
            }
        }
    }
}