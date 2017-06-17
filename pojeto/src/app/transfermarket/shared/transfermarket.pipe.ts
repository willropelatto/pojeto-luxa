import { Transfermarket } from './transfermarket.model';
import { Injectable, Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'transferMarketNameFilter'
})

@Injectable()
export class TransfermarketNameFilter implements PipeTransform {
  transform(transfermarkets: Transfermarket[], name: any, position: any, rating: any, bidValue: any, originalValue: any, hasBid: any, bidAproved: any): any {
    return transfermarkets.filter((transfermarket) => {
      if (name && transfermarket.name.toLowerCase().indexOf(name.toLowerCase()) === -1) {
        return false;
      }
      if (position && transfermarket.position.toLowerCase().indexOf(position.toLowerCase()) === -1) {
        return false;
      }
      if (bidValue && transfermarket.bidValue != bidValue) {
        return false;
      }
      if (rating && transfermarket.rating != rating) {
        return false;
      }
      if (originalValue && transfermarket.originalValue != originalValue) {
        return false;
      }
      if (hasBid && transfermarket.hasBid != hasBid) {
        return false;
      }
      if (bidAproved && transfermarket.bidAproved != bidAproved) {
        return false;
      }
      return true;
    });
  }
}

@Pipe({
  name: 'transferMarketPositionFilter'
})
@Injectable()
export class TransfermarketPositionFilter implements PipeTransform {
  transform(transfermarkets: Transfermarket[], position: any): any {
    return transfermarkets.filter((transfermarket) => {
      return (transfermarket.position.indexOf(position) !== -1);
    });
  }
}


