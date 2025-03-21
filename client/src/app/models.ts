// You may use this file to create any models
export interface menuItem{
    id: string,
    name: string,
    description: string,
    price: number
}

export interface Order{
    username: string,
    password: string,
    items: menuItem[]
}

export interface ServerReceipt{
    orderId : string,
    paymentId: string,
    total: number,
    timestamp: Date
}