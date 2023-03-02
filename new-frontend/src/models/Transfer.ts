export interface Transfer {
    id?: number,
    accountId?: number,
    amount?: number,
    isDeposit?: boolean,
    time?: string,
    description?: string
}